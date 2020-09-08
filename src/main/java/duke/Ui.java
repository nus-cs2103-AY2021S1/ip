package duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UndoCommand;
import duke.task.Task;

/**
 * Represents all user interaction with Duke.
 */
public class Ui {
    /** Class-wide variable to know whether to continue running user interface. */
    protected static boolean isRunning = true;
    protected static String logo;
    protected final TaskListHandler handler;
    protected final Storage storage;


    /**
     * Initializes the Ui with the task list and storage.
     *
     * @param handler Contains the task list and its operations.
     * @param storage Contains all operations related to loading and saving of tasks to disk.
     */
    public Ui(TaskListHandler handler, Storage storage) {
        this.handler = handler;
        this.storage = storage;
    }

    /**
     * Stops the user interface and terminates the program.
     */
    public static void stopRunning() {
        isRunning = false;
    }

    /**
     * Runs the user interface which scans for input, parsing it and executes valid commands.
     * Terminates when empty line is detected, when input file is used.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            // Listens for input
            try {
                String input = scanner.nextLine();
                Command c = Parser.parse(input, handler);
                c.execute(handler, storage, input);
                System.out.println();
            } catch (NoSuchElementException e1) {
                // Encounter end of file, terminate
                isRunning = false;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                DukeException.tryAgain();
            }
        }
    }

    /**
     * Provides indentation for formatting.
     *
     * @param times The number of 4 spaces to be printed.
     */
    public static void indent(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("    ");
        }
    }

    /**
     * Prints success message and list size after task added/deleted.
     *
     * @param operation Type of command.
     * @param currentTask Task that was modified.
     * @param taskList Task list.
     */
    public static void printSuccess(String operation, Task currentTask, ArrayList<Task> taskList) {
        // Prints success message and list size after task added/deleted
        switch (operation) {
        case "add":
            System.out.print("Yes! I have successfully added:\n");
            break;
        case "delete":
            System.out.print("Alright! I've removed this task:\n");
            break;
        case "done":
            System.out.println("Good job! You completed:");
            break;
        case "find":
            System.out.println("I have found the matching tasks in your list: ");
            for (Task t: taskList) {
                indent(1);
                System.out.println(t);
            }
            break;
        default:
            assert false : "Error printing success message of " + operation;
            return;
        }
        indent(1);
        System.out.println(currentTask);
        System.out.println();
    }

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        System.out.println(getGreeting());
    }

    /**
     * Retrieves the greeting in string format for GUI.
     *
     * @return Greeting.
     */
    public static String getGreeting() {
        setLogo();
        String greeting = "Hey! I'm Duke the chatbot!";
        String doForYou = "What can I do for you?";
        return logo + "\n"
            + greeting + "\n"
            + doForYou + "\n";
    }

    /**
     * Initializes the duke logo.
     */
    public static void setLogo() {
        logo = "DUKE!";
    }

    /**
     * Provides the user with how to use an individual command or all commands if none specified.
     *
     * @param input Command specified by the user.
     * @throws DukeException if Invalid command entered after help.
     */
    public static void printHelp(String input) throws DukeException {
        if (input.contains(" ")) {
            String commandGiven = input.trim().split(" ")[1];
            switch (commandGiven) {
            case "bye":
                System.out.println(ByeCommand.COMMAND_USAGE);
                break;
            case "list":
                System.out.println(ListCommand.COMMAND_USAGE);
                break;
            case "done":
                System.out.println(DoneCommand.COMMAND_USAGE);
                break;
            case "delete":
                System.out.println(DeleteCommand.COMMAND_USAGE);
                break;
            case "clear":
                System.out.println(ClearCommand.COMMAND_USAGE);
                break;
            case "deadline":
                System.out.println(DeadlineCommand.COMMAND_USAGE);
                break;
            case "event":
                System.out.println(EventCommand.COMMAND_USAGE);
                break;
            case "todo":
                System.out.println(TodoCommand.COMMAND_USAGE);
                break;
            case "find":
                System.out.println(FindCommand.COMMAND_USAGE);
                break;
            case "undo":
                System.out.println(UndoCommand.COMMAND_USAGE);
                break;
            default:
                throw new DukeException("\u2639 Whoops, " + '"' + commandGiven + '"' + " is not a valid command for "
                    + "help! \n"
                    + "Try entering 'help' to show all commands.");
            }
        } else {
            System.out.println("Here's how to use all the commands!");
            System.out.println();
            int counter = 1;
            System.out.println(counter + ") " + DeadlineCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + EventCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + TodoCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ByeCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ListCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + FindCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ClearCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + UndoCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + DoneCommand.COMMAND_USAGE);
            System.out.println();
            System.out.println();
            counter++;
            System.out.println(counter + ") " + DeleteCommand.COMMAND_USAGE);
        }
    }
}
