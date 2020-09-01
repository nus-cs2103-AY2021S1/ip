package duke;

import java.time.LocalDateTime;
import java.util.Scanner;

import duke.enums.Command;
import duke.exceptions.DukeException;
import duke.messages.DukeResponse;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;
import duke.utils.DateTimeParser;
import duke.utils.PrettyPrinter;
import duke.utils.ResourceHandler;

/**
 * A read-eval-print loop (REPL) that reads in a command from the user, executes it, and prints out the result.
 */
public class Repl {
    // Formatting
    /** Number of spaces to prefix each line with. */
    private static final int LEFT_PADDING_SIZE = 4;
    /** Number of underscores each divider should be made up of. */
    private static final int DIVIDER_LENGTH = 60;

    /** {@code Scanner} object which reads in user input. */
    private static final Scanner scanner = new Scanner(System.in);
    /** {@code PrettyPrinter} object for formatting the REPL output. */
    private static final PrettyPrinter prettyPrinter = new PrettyPrinter(LEFT_PADDING_SIZE, DIVIDER_LENGTH);
    /** {@code TaskManager} object to keep track of tasks. */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(ResourceHandler.getString("repl.greeting"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            DukeResponse dukeResponse = getResponse(line);
            prettyPrinter.print(dukeResponse.toString());
            if (dukeResponse.shouldExit()) {
                break;
            }
        }
    }

    /**
     * Processes the user's input and returns a {@code DukeResponse}.
     *
     * @param input the user's input
     * @return a {@code DukeResponse}
     */
    public static DukeResponse getResponse(String input) {
        String firstToken = input.split(" ")[0];
        // This should never be displayed as all possible responses should already be covered.
        String response = ResourceHandler.getString("repl.internalError");
        boolean shouldExit = false;
        try {
            Command command = Command.valueOf(firstToken.toUpperCase());
            // Check that the user input is of the correct format for the command.
            command.validate(input);
            switch (command) {
            case BYE:
                response = ResourceHandler.getString("repl.farewell");
                shouldExit = true;
                break;
            case DEADLINE: {
                String lineWithoutCommand = input.replaceFirst("^deadline", "");
                String[] args = lineWithoutCommand.split("/by", 2);
                String deadlineName = args[0].trim();
                String dueDateString = args[1].trim();
                LocalDateTime dueDate = DateTimeParser.parseDateTime(dueDateString);
                response = taskManager.addTask(new Deadline(deadlineName, dueDate));
                break;
            }
            case DELETE: {
                String lineWithoutCommand = input.replaceFirst("^delete", "");
                String listIndexStr = lineWithoutCommand.trim();
                // `listIndexStr` is guaranteed to be a string made up of only digit characters.
                int listIndex = Integer.parseInt(listIndexStr) - 1;
                try {
                    response = taskManager.removeTask(listIndex);
                } catch (IndexOutOfBoundsException e) {
                    response = ResourceHandler.getString("repl.invalidTaskIndex");
                }
                break;
            }
            case DONE: {
                String lineWithoutCommand = input.replaceFirst("^done", "");
                String listIndexStr = lineWithoutCommand.trim();
                // `listIndexStr` is guaranteed to be a string made up of only digit characters.
                int listIndex = Integer.parseInt(listIndexStr) - 1;
                try {
                    response = taskManager.markAsDone(listIndex);
                } catch (IndexOutOfBoundsException e) {
                    response = ResourceHandler.getString("repl.invalidTaskIndex");
                }
                break;
            }
            case EVENT: {
                String lineWithoutCommand = input.replaceFirst("^event", "");
                String[] args = lineWithoutCommand.split("/at", 2);
                String eventName = args[0].trim();
                String dateTimeString = args[1].trim();
                LocalDateTime dateTime = DateTimeParser.parseDateTime(dateTimeString);
                response = taskManager.addTask(new Event(eventName, dateTime));
                break;
            }
            case FIND: {
                String lineWithoutCommand = input.replaceFirst("^find", "");
                String[] searchKeywords = lineWithoutCommand.trim().split("\\s+");
                prettyPrinter.print(taskManager.getMatchingTasks(searchKeywords));
                break;
            }
            case LIST: {
                response = taskManager.toString();
                break;
            }
            case OVERDUE: {
                response = taskManager.getOverdueTasks();
                break;
            }
            case TODO: {
                String lineWithoutCommand = input.replaceFirst("^todo", "");
                String toDoName = lineWithoutCommand.trim();
                response = taskManager.addTask(new ToDo(toDoName));
                break;
            }
            case UPCOMING: {
                response = taskManager.getUpcomingTasks();
                break;
            }
            default:
                break;
            }
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (IllegalArgumentException e) {
            response = ResourceHandler.getString("repl.unknownCommand");
        }
        return new DukeResponse(response, shouldExit);
    }
}
