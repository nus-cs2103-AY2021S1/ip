package duke.logic;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Parser responsible for parsing Strings
 * to generate tasks and commands for the Duke bot.
 */
public class Parser {

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";

    /** Format for date and time used when user inputs a task */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /** Format for date and time used when Duke saves a task list */
    private static final DateTimeFormatter SAVED_DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

    private static LocalDateTime parseDate(String fullDateTime, DateTimeFormatter format) {
        return LocalDateTime.parse(fullDateTime, format);
    }

    /**
     * Parses a line read from the task list from the storage space
     * and generates a corresponding Task to be added to the live TaskList.
     *
     * @param fullTask The line from the .txt file to be parsed
     * @return The corresponding Task.
     * @throws DukeException If the line contains an incorrect format.
     */
    public static Task parseTask(String fullTask) throws DukeException {
        String[] splitTask = fullTask.split(" \\| ");
        String type = splitTask[0];
        String description = splitTask[2];
        boolean isDone = Integer.parseInt(splitTask[1]) == 1;

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, isDone, parseDate(splitTask[3], SAVED_DATE_TIME_FORMAT));
        case "E":
            return new Event(description, isDone, parseDate(splitTask[3], SAVED_DATE_TIME_FORMAT));
        default:
            throw new DukeException(ErrorMessage.UNABLE_READ_TASK_HISTORY.getMessage());
        }
    }

    private static Deadline parseDeadline(String fullDeadline) {
        String[] splitDeadline = fullDeadline.split(DEADLINE_DELIMITER);
        String description = splitDeadline[0].trim();
        LocalDateTime dateTime = parseDate(splitDeadline[1].trim(), INPUT_DATE_TIME_FORMAT);
        return new Deadline(description, dateTime);
    }

    private static Event parseEvent(String fullEvent) {
        String[] splitEvent = fullEvent.split(EVENT_DELIMITER);
        String description = splitEvent[0].trim();
        LocalDateTime dateTime = parseDate(splitEvent[1].trim(), INPUT_DATE_TIME_FORMAT);
        return new Event(description, dateTime);
    }

    /**
     * Parses a line read from the user input and generates a
     * corresponding Command to be executed by Duke.
     *
     * @param fullCommand The user input to be parsed.
     * @return The corresponding Command
     * @throws DukeException If the command type cannot be recognised or is in the wrong format
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String type = splitCommand[0];

        try {
            switch (type) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            case "find":
                return new FindCommand(splitCommand[1]);
            case "todo":
                return new AddCommand(new Todo(splitCommand[1]));
            case "deadline":
                return new AddCommand(parseDeadline(splitCommand[1]));
            case "event":
                return new AddCommand(parseEvent(splitCommand[1]));
            case "done":
                return new DoneCommand(Integer.parseInt(splitCommand[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(splitCommand[1]));
            default:
                throw new DukeException(ErrorMessage.INVALID_COMMAND_TYPE.getMessage());
            }

        } catch (DateTimeException dateTimeException) {
            throw new DukeException(ErrorMessage.INVALID_DATE_TIME_FORMAT.getMessage());
        } catch (Exception exception) {
            throw new DukeException(ErrorMessage.INVALID_COMMAND_FORMAT.getMessage());
        }
    }

}
