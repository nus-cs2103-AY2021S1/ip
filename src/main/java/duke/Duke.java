package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Duke class to encapsulate the behaviour of a task manager */
@SuppressWarnings("checkstyle:Regexp")
public class Duke {
    private static Scanner scan = new Scanner(System.in);
    private static final String SAVE_PATH = "./SaveData.txt";
    private static enum AcceptedCommands {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        DONE,
        BYE,
        DELETE,
        CLEAR,
        HELLO,
        FIND,
        FIXEDTASK,
    }
    private Storage storage;
    private TaskList tasks;

    Duke(String filepath) {
        storage = new Storage(filepath);
        tasks = storage.loadTask();
    }

    /** Driver method for Duke */
    private String run(String userInput) {
        while (!userInput.equals("bye")) {
            String command;
            String details;
            try {
                command = Parser.getCommand(userInput);
                details = Parser.getDetails(userInput);

                // Catch illegal commands
                checkIllegalCommand(command);
                // Catch missing content
                checkMissingArgument(command, details);
                // Check indexing out of bounds
                checkExistingTask(command, details, tasks.length());
                // Check illegal argument
                checkIllegalArgument(command, details);
            } catch (DukeIllegalCommandException | DukeMissingArgumentException | DukeTaskOutOfBoundsException e) {
                return (e.toString());
            } catch (DateTimeParseException e) {
                return ("date time wrong");
            } catch (Exception e) {
                return ("Write a number pls");
            }

            String a = actionPicker(userInput);
            if (!a.equals("")) {
                return a;
            }
            // Gets the new input
            userInput = scan.nextLine();
        }

        return Ui.prettyPrint("Bye. Hope to see you again soon!");
    }
    /**
     * Decide what action to perform
     *
     * @param userInput The line of user input
     * @return The output string to be displayed
     */
    private String actionPicker(String userInput) {
        String command = Parser.getCommand(userInput);
        String details = Parser.getDetails(userInput);
        // Decide the actions
        String[] description;
        Task taskToUpdate;
        try {
            switch (command) {
            case "list":
                return Ui.prettyPrint(tasks);
            case "done":
                taskToUpdate = tasks.updateTaskStatus(Parser.getIndex(userInput), true);
                storage.saveTask(tasks);
                return Ui.prettyPrint("Nice! I've marked this task as done: \n" + "\t" + taskToUpdate);
            case "todo":
                taskToUpdate = tasks.addTask(new ToDo(Parser.getDetails(userInput)));
                storage.saveTask(tasks);
                return Ui.updateTaskText("added", taskToUpdate, tasks.length());
            case "event":
                description = Parser.stringSplit(details, " /at ");
                taskToUpdate = tasks.addTask(new Event(description[0], LocalDate.parse(description[1])));
                storage.saveTask(tasks);
                return Ui.updateTaskText("added", taskToUpdate, tasks.length());
            case "deadline":
                description = Parser.stringSplit(details, " /by ");
                taskToUpdate = tasks.addTask(new Deadline(description[0], LocalDate.parse(description[1])));
                storage.saveTask(tasks);
                return Ui.updateTaskText("added", taskToUpdate, tasks.length());
            case "delete":
                taskToUpdate = tasks.removeTask(Parser.getIndex(userInput));
                storage.saveTask(tasks);
                return Ui.updateTaskText("removed", taskToUpdate, tasks.length());
            case "fixedtask":
                description = Parser.stringSplit(details, " /for ");
                taskToUpdate = tasks.addTask(new FixedDurationTask(description[0], description[1]));
                storage.saveTask(tasks);
                return Ui.updateTaskText("added", taskToUpdate, tasks.length());
            case "clear":
                return ("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            case "hello":
                return Ui.greet();
            case "find":
                return Ui.prettyPrint(tasks.contains(details));
            default:
                return "";
            }
        } catch (IOException e) {
            return "file not found";
        }
    }

    /**
     * Checks if command user input is valid
     *
     * @param command Command to check against valid commands
     * @throws DukeIllegalCommandException
     */
    private static void checkIllegalCommand(String command) throws DukeIllegalCommandException {
        for (AcceptedCommands i : AcceptedCommands.values()) {
            if (command.equalsIgnoreCase(i.name())) {
                return;
            }
        }

        throw new DukeIllegalCommandException();
    }

    /**
     * Checks if command of user is missing arguments
     *
     * @param command Command to check
     * @param details Checks if argument is missing in details
     * @throws DukeMissingArgumentException
     */
    private static void checkMissingArgument(String command, String details) throws DukeMissingArgumentException {
        if (!(command.equalsIgnoreCase(AcceptedCommands.LIST.name())
                || command.equalsIgnoreCase(AcceptedCommands.BYE.name())
                || command.equalsIgnoreCase(AcceptedCommands.CLEAR.name())
                || command.equalsIgnoreCase(AcceptedCommands.HELLO.name()))
                && (details.isEmpty())) {
            throw new DukeMissingArgumentException(command);
        }
    }

    /**
     * Checks for index out of bounds error
     *
     * @param command Command to check
     * @param details Details of command
     * @param max Maximum length of TaskList
     * @throws DukeTaskOutOfBoundsException
     */
    private static void checkExistingTask(String command, String details, int max) throws DukeTaskOutOfBoundsException {
        if ((command.equalsIgnoreCase(AcceptedCommands.DONE.name()))
                || command.equalsIgnoreCase(AcceptedCommands.DELETE.name())) {
            if ((Integer.parseInt(details) < 1) || (Integer.parseInt(details) >= (max + 1))) {
                throw new DukeTaskOutOfBoundsException(command);
            }
        }
    }

    /**
     * Checks for illegal arguments
     *
     * @param command Command to check
     * @param details Check if arument is valid
     * @throws Exception
     */
    private static void checkIllegalArgument(String command, String details) throws Exception {
        if (command.equalsIgnoreCase(AcceptedCommands.DONE.name())
                || command.equalsIgnoreCase(AcceptedCommands.DELETE.name())) {
            Integer.parseInt(details);
        }
    }

    public static void main(String[] args) {
        new Duke(SAVE_PATH).run("hello");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return run(input);
    }
}
