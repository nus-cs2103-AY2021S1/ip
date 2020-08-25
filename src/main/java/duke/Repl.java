package duke;

import duke.enums.Command;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;
import duke.utils.DateTimeParser;
import duke.utils.PrettyPrinter;
import duke.utils.ResourceHandler;

import java.time.LocalDateTime;
import java.util.Scanner;

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
    /** {@code TaskManager} object to keep track of tasks */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(ResourceHandler.getString("repl.greeting"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String firstToken = line.split(" ")[0];
            try {
                Command command = Command.valueOf(firstToken.toUpperCase());
                // Check that the user input is of the correct format for the command.
                command.validate(line);
                switch (command) {
                    case BYE:
                        prettyPrinter.print(ResourceHandler.getString("repl.farewell"));
                        return;
                    case DEADLINE: {
                        String lineWithoutCommand = line.replaceFirst("^deadline", "");
                        String[] args = lineWithoutCommand.split("/by", 2);
                        String deadlineName = args[0].trim();
                        String dueDateString = args[1].trim();
                        LocalDateTime dueDate = DateTimeParser.parseDateTime(dueDateString);
                        prettyPrinter.print(taskManager.addTask(new Deadline(deadlineName, dueDate)));
                        break;
                    }
                    case DELETE: {
                        String lineWithoutCommand = line.replaceFirst("^delete", "");
                        String listIndexStr = lineWithoutCommand.trim();
                        // `listIndexStr` is guaranteed to be a string made up of only digit characters.
                        int listIndex = Integer.parseInt(listIndexStr) - 1;
                        try {
                            prettyPrinter.print(taskManager.removeTask(listIndex));
                        } catch (IndexOutOfBoundsException e) {
                            prettyPrinter.print(ResourceHandler.getString("repl.invalidTaskIndex"));
                        }
                        break;
                    }
                    case DONE: {
                        String lineWithoutCommand = line.replaceFirst("^done", "");
                        String listIndexStr = lineWithoutCommand.trim();
                        // `listIndexStr` is guaranteed to be a string made up of only digit characters.
                        int listIndex = Integer.parseInt(listIndexStr) - 1;
                        try {
                            prettyPrinter.print(taskManager.markAsDone(listIndex));
                        } catch (IndexOutOfBoundsException e) {
                            prettyPrinter.print(ResourceHandler.getString("repl.invalidTaskIndex"));
                        }
                        break;
                    }
                    case EVENT: {
                        String lineWithoutCommand = line.replaceFirst("^event", "");
                        String[] args = lineWithoutCommand.split("/at", 2);
                        String eventName = args[0].trim();
                        String dateTimeString = args[1].trim();
                        LocalDateTime dateTime = DateTimeParser.parseDateTime(dateTimeString);
                        prettyPrinter.print(taskManager.addTask(new Event(eventName, dateTime)));
                        break;
                    }
                    case LIST: {
                        prettyPrinter.print(taskManager.toString());
                        break;
                    }
                    case OVERDUE: {
                        prettyPrinter.print(taskManager.getOverdueTasks());
                        break;
                    }
                    case TODO: {
                        String lineWithoutCommand = line.replaceFirst("^todo", "");
                        String toDoName = lineWithoutCommand.trim();
                        prettyPrinter.print(taskManager.addTask(new ToDo(toDoName)));
                        break;
                    }
                    case UPCOMING: {
                        prettyPrinter.print(taskManager.getUpcomingTasks());
                        break;
                    }
                }
            } catch (DukeException e) {
                prettyPrinter.print(e.getMessage());
            } catch (IllegalArgumentException e) {
                prettyPrinter.print(ResourceHandler.getString("repl.unknownCommand"));
            }
        }
    }
}
