package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.PriorityCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.DukeException.ExceptionType;
import duke.task.Priority;

/**
 * Contains methods for processing user input, converting it to a {@link Command}
 * that can be executed.
 */

public class Parser {

    /**
     * Converts user input into a {@link Command} that can be executed.
     *
     * @param input String input entered by user.
     * @return {@link Command} that can be executed.
     * @throws DukeException When no integer input is provided after "done" or "delete".
     * @throws DukeException When no description is provided for a task.
     * @throws DukeException When there is no date flagged by "/by" for a deadline, or "/at" for an event.
     * @throws DukeException When the date input for a deadline or event does not follow YYYY-MM-DD format.
     * @throws DukeException When unsupported input is entered.
     */
    public static Command parse(String input) throws DukeException {
        try {
            //Splits input into parts by spaces
            String[] inputByParts = input.split(" ", 2);

            //Runs through the list of acceptable commands
            if (input.equals("bye")) {
                return new ExitCommand();
            } else if (input.equals("list")) {
                return new ListCommand();
            } else if (inputByParts[0].equals("delete")) {
                int taskNumber = Integer.parseInt(inputByParts[1]);
                return new DeleteCommand(taskNumber);
            } else if (inputByParts[0].equals("done")) {
                int taskNumber = Integer.parseInt(inputByParts[1]);
                return new MarkDoneCommand(taskNumber);
            } else if (inputByParts[0].equals("find")) {
                String toFind = inputByParts[1];
                return new FindCommand(toFind);
            } else if (inputByParts[0].equals("priority")) {
                String[] taskInfo = inputByParts[1].split(" ");
                int taskNumber = Integer.parseInt(taskInfo[0]);
                Priority priority = getPriority(taskInfo[1]);
                return new PriorityCommand(taskNumber, priority);
            } else {
                String taskType = inputByParts[0];
                validateTaskType(taskType);
                String description = inputByParts[1];
                return parseNewTaskInput(taskType, description);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ExceptionType.NO_DESCRIPTION_PROVIDED);
        } catch (NumberFormatException e) {
            throw new DukeException(ExceptionType.INVALID_NUMBER_INPUT);
        }
    }

    private static Priority getPriority(String inputByPart) throws DukeException {
        switch (inputByPart) {
        case "high":
            return Priority.HIGH;
        case "medium":
            return Priority.MEDIUM;
        case "low":
            return Priority.LOW;
        default:
            throw new DukeException(ExceptionType.INVALID_PRIORITY);
        }
    }

    private static Command parseNewTaskInput(String taskType, String description) throws DukeException {
        try {
            switch (taskType) {
            case "todo":
                return new TodoCommand(description);
            case "deadline":
                String[] deadlineDetails = description.split("/by", 2);
                String deadlineDesc = deadlineDetails[0].trim();
                LocalDate deadlineTime = LocalDate.parse(deadlineDetails[1].trim());
                return new DeadlineCommand(deadlineDesc, deadlineTime);
            default:
                assert taskType.equals("event") : "The last case in the switch statement should be event.";
                String[] eventDetails = description.split("/at", 2);
                String eventDesc = eventDetails[0].trim();
                LocalDate eventTime = LocalDate.parse(eventDetails[1].trim());
                return new EventCommand(eventDesc, eventTime);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.ExceptionType.NO_TIMEFRAME_PROVIDED);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ExceptionType.INVALID_TIMEFRAME);
        }
    }

    private static void validateTaskType(String taskType) throws DukeException {
        if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
            throw new DukeException(taskType, DukeException.ExceptionType.UNEXPECTED_INPUT_IN_FILE);
        }
    }
}
