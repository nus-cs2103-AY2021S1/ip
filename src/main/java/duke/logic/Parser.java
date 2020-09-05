package duke.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a helper class that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns a command instance after parsing the input command.
     *
     * @param fullCommand The command to be parsed.
     * @return A command instance which its type is determined by the input command.
     * @throws DukeException If the input command is deemed invalid or the format is incorrect.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String action = splitCommand[0];

        boolean hasOnlyOneWord = splitCommand.length < 2;
        boolean isMissingTaskDescription = isTask(action) && hasOnlyOneWord;
        boolean isMissingTaskNumber = isDoneOrDeleteCommand(action) && hasOnlyOneWord;
        boolean isMissingFindKeyword = action.equals("find") && hasOnlyOneWord;

        if (isMissingTaskDescription) {
            throw new DukeException("OOPS!!! Description of a task cannot be empty :(\n");
        }
        if (isMissingTaskNumber) {
            throw new DukeException("Missing task number! "
                    + "Please ensure to key in the task number :)\n");
        }
        if (isMissingFindKeyword) {
            throw new DukeException("Please indicate the keyword which you want to find.\n");
        }

        Command command;
        try {
            switch (action) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "todo": {
                String description = splitCommand[1];
                command = new TodoCommand(description);
                break;
            }
            case "deadline": {
                String[] splitDeadline = splitCommand[1].split("/by");
                boolean isMissingKeyword = splitDeadline.length < 2;
                if (isMissingKeyword) {
                    throw new DukeException("Please indicate a deadline using the \"/by\" keyword.\n");
                }
                String description = splitDeadline[0].trim();
                String by = splitDeadline[1].trim();
                LocalDate date = LocalDate.parse(by);
                command = new DeadlineCommand(description, date);
                break;
            }
            case "event": {
                String[] splitEvent = splitCommand[1].split("/at");
                boolean isMissingKeyword = splitEvent.length < 2;
                if (isMissingKeyword) {
                    throw new DukeException("Please indicate a timing using the \"/at\" keyword.\n");
                }
                String description = splitEvent[0].trim();
                String at = splitEvent[1].trim();
                LocalDate date = LocalDate.parse(at);
                command = new EventCommand(description, date);
                break;
            }
            case "done": {
                String taskNumber = splitCommand[1];
                command = new DoneCommand(taskNumber);
                break;
            }
            case "delete": {
                String taskNumber = splitCommand[1];
                command = new DeleteCommand(taskNumber);
                break;
            }
            case "find":
                String keyword = splitCommand[1];
                String[] keywords = keyword.split(" ");
                command = new FindCommand(keywords);
                break;
            default:
                command = new InvalidCommand();
                break;
            }
            return command;
        } catch (DateTimeParseException e) {
            String errorMessage = "Invalid date format! "
                    + "Please use the proper date format i.e. yyyy-MM-dd\n";
            throw new DukeException(errorMessage);
        }
    }

    private static boolean isTask(String action) {
        return action.equals("todo")
                || action.equals("deadline") || action.equals("event");
    }

    private static boolean isDoneOrDeleteCommand(String action) {
        return action.equals("done") || action.equals("delete");
    }

    /**
     * Returns the task in the list after parsing the task number.
     * @param taskNumber The task number.
     * @param tasks The list of tasks.
     * @return The task in the list corresponding to the correct task number.
     * @throws DukeException If the input could not be parsed as a number or the number out of range.
     */
    public static Task parseTaskNumber(String taskNumber, List<Task> tasks) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            return tasks.get(index);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            String errorMessage = "Invalid task number! Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }
}
