package duke.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeDateTimeParseException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskNumberException;
import duke.exception.DukeMissingFindKeywordException;
import duke.exception.DukeMissingTaskDescriptionException;
import duke.exception.DukeMissingTaskKeywordException;
import duke.exception.DukeMissingTaskNumberException;
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
        CommandType type = CommandType.getCommandType(splitCommand[0]);

        boolean hasOnlyOneWord = splitCommand.length < 2;
        boolean isMissingTaskDescription = isTask(type) && hasOnlyOneWord;
        boolean isMissingTaskNumber = isDoneOrDeleteCommand(type) && hasOnlyOneWord;
        boolean isMissingFindKeyword = type == CommandType.FIND && hasOnlyOneWord;

        if (isMissingTaskDescription) {
            throw new DukeMissingTaskDescriptionException(splitCommand[0]);
        }
        if (isMissingTaskNumber) {
            throw new DukeMissingTaskNumberException();
        }
        if (isMissingFindKeyword) {
            throw new DukeMissingFindKeywordException();
        }

        Command command;
        try {
            switch (type) {
            case BYE:
                command = new ExitCommand();
                break;
            case LIST:
                command = new ListCommand();
                break;
            case TODO: {
                String description = splitCommand[1];
                command = new TodoCommand(description);
                break;
            }
            case DEADLINE: {
                String[] splitDeadline = splitCommand[1].split("/by");
                boolean isMissingKeyword = splitDeadline.length < 2;
                if (isMissingKeyword) {
                    throw new DukeMissingTaskKeywordException("\"/by\"");
                }
                String description = splitDeadline[0].trim();
                String by = splitDeadline[1].trim();
                LocalDate date = LocalDate.parse(by);
                command = new DeadlineCommand(description, date);
                break;
            }
            case EVENT: {
                String[] splitEvent = splitCommand[1].split("/at");
                boolean isMissingKeyword = splitEvent.length < 2;
                if (isMissingKeyword) {
                    throw new DukeMissingTaskKeywordException("\"/at\"");
                }
                String description = splitEvent[0].trim();
                String at = splitEvent[1].trim();
                LocalDate date = LocalDate.parse(at);
                command = new EventCommand(description, date);
                break;
            }
            case DONE: {
                String taskNumber = splitCommand[1];
                command = new DoneCommand(taskNumber);
                break;
            }
            case DELETE: {
                String taskNumber = splitCommand[1];
                command = new DeleteCommand(taskNumber);
                break;
            }
            case FIND:
                String keyword = splitCommand[1];
                String[] keywords = keyword.split(" ");
                command = new FindCommand(keywords);
                break;
            default:
                command = new InvalidCommand(fullCommand);
                break;
            }
            return command;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException();
        }
    }

    private static boolean isTask(CommandType type) {
        return type == CommandType.TODO
                || type == CommandType.DEADLINE || type == CommandType.EVENT;
    }

    private static boolean isDoneOrDeleteCommand(CommandType type) {
        return type == CommandType.DONE || type == CommandType.DELETE;
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
            throw new DukeInvalidTaskNumberException(taskNumber);
        }
    }
}
