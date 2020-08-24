package taskbot.main;

import java.util.Scanner;

import taskbot.logic.Taskbot;

import taskbot.exceptions.EmptyArgumentException;
import taskbot.exceptions.InvalidCommandException;
import taskbot.exceptions.InvalidIndexException;

import taskbot.storage.Storage;
import taskbot.task.TaskList;

import static taskbot.helper.Helper.handleException;

/**
 * This is the main class which handles user input.
 */
public class Main {
    //Commands for the bot to execute
    public enum Command {
        LIST, DONE, NONE,
        TODO, DEADLINE, EVENT,
        EXIT, DELETE
    }

    public static void main(String[] args) throws Exception {
        //The logo to be used for TaskBot
        String logo = "___________              __   __________        __   \n" +
                "\\__    ___/____    _____|  | _\\______   \\ _____/  |_ \n" +
                "  |    |  \\__  \\  /  ___/  |/ /|    |  _//  _ \\   __\\\n" +
                "  |    |   / __ \\_\\___ \\|    < |    |   (  <_> )  |  \n" +
                "  |____|  (____  /____  >__|_ \\|______  /\\____/|__|  \n" +
                "               \\/     \\/     \\/       \\/             ";

        //Initialise Taskbot
        Taskbot tb = new Taskbot(logo);

        //Prints the title to the console and greets the user
        tb.printTitle();

        //Create a new storage database
        Storage storage = new Storage();

        //Initialise the task list with the database
        TaskList taskList = new TaskList(storage);

        //Greet the user
        tb.greet();
        //Initialises a Scanner to take in user input
        Scanner sc = new Scanner(System.in);

        Command cmd = Command.NONE;
            //Loops while the user does not input "bye"
            label:
            while (sc.hasNextLine()) {
                try {
                int taskIndex = -1;
                String input = sc.nextLine();

                //Splits the string to find keywords for commands
                String[] key = input.split(" ", 2);
                cmd = getCommand(key[0]);

                //instructs the bot given the command, throwing the required exceptions when necessary
                switch (cmd) {
                    case LIST:
                        taskList.listTasks();
                        break;
                    case DONE:
                        if (key.length == 1 || key[1].strip().length() == 0) {
                            throw new EmptyArgumentException("Please enter the index of the task you wish to complete.");
                        }
                        taskIndex = Integer.parseInt(key[1]) - 1;
                        taskList.completeTask(taskIndex);
                        break;
                    case DELETE:
                        if (key.length == 1 || key[1].strip().length() == 0) {
                            throw new EmptyArgumentException("Please enter the index of the task you wish to delete.");
                        }
                        taskIndex = Integer.parseInt(key[1]) - 1;
                        taskList.deleteTask(taskIndex);
                        break;
                    case TODO:
                        if (key.length == 1 || key[1].strip().length() == 0) {
                            throw new EmptyArgumentException("The description of a todo cannot be empty. Please input a valid description.");
                        }
                        taskList.addTodoTask(key[1]);
                        break;
                    case EVENT:
                        if (key.length == 1 || key[1].strip().length() == 0) {
                            throw new EmptyArgumentException("The description of an event cannot be empty. Please input a valid description.");
                        }
                        taskList.addEventTask(key[1]);
                        break;
                    case DEADLINE:
                        if (key.length == 1 || key[1].strip().length() == 0) {
                            throw new EmptyArgumentException("The description of a deadline cannot be empty. Please input a valid description.");
                        }
                        taskList.addDeadlineTask(key[1]);
                        break;
                    case NONE:
                        throw new InvalidCommandException("That was not a valid command. Please try again.");
                    case EXIT:
                        break label;
                }
                //Reset the cmd for the next user input
                cmd = Command.NONE;
                } catch (NumberFormatException e) {
                    InvalidIndexException wfe = new InvalidIndexException("Please enter a valid index for the task.");
                    handleException(wfe);
                } catch (InvalidCommandException | EmptyArgumentException e) {
                    handleException(e);
                }
            }

        //The bot says bye and the program terminates
        tb.sayBye();
    }

    /**
     * Returns the proper Command given the input
     * @param input String to be checked
     * @return a Command enum
     */
    public static Command getCommand(String input) {
        switch (input) {
            case "bye":
                return Command.EXIT;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "delete":
                return Command.DELETE;
            default:
                return Command.NONE;
        }
    }

}
