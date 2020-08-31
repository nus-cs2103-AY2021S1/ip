package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The Parser class takes in inputs from the user and convert into commands.
 */
public class Parser {

    private static final List<String> DATE_FORMATS = Arrays.asList("yyyy/MM/dd HHmm", "y/M/d HHmm", "y-M-d HHmm");
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

    /** Returns the command based on user input. */
    public static Command parse(String input) throws DukeException {
        int i = input.trim().indexOf(' ');
        String command = input;
        String detail = "";
        if (i > 0) {
            command = input.substring(0, i);
            detail = input.substring(i).trim();
        }

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(parseNumber(detail));
        case TODO:
            return new TodoCommand(parseTodo(detail));
        case EVENT:
            return new EventCommand(parseEvent(detail));
        case DEADLINE:
            return new DeadlineCommand(parseDeadline(detail));
        case DELETE:
            return new DeleteCommand(parseNumber(detail));
        case FIND:
            return new FindCommand(detail.trim());
        default:
            throw new DukeException("Oops! I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Parse the date given by user.
     * @param dateString Date provided by user in the form of string.
     * @return Date in LocalDateTime.
     */
    public static LocalDateTime parseDate(String dateString) {
        for (String format : DATE_FORMATS) {
            try {
                return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    /**
     * Return the ToDo the user specified.
     * @param input The todo details given by user.
     * @return A ToDo with the input given.
     */
    public static ToDo parseTodo(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Oops! Todo cannot be empty");
        }
        return new ToDo(input);
    }

    /**
     * Return the Deadline the user specified.
     * @param input The deadline details given by user.
     * @return A Deadline with the input given.
     */
    public static Deadline parseDeadline(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("Oops! Deadline cannot be empty");
        }
        String[] arr = input.split("/by");
        if (arr.length == 1 || arr[0].trim().equals("")) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = parseDate(arr[1].trim());
        if (date == null) {
            throw new DukeException("Oops! Format of date and time might be wrong.");
        }
        return new Deadline(detail, date);
    }

    /**
     * Return the Event the user specified.
     * @param input The event details given by user.
     * @return A Event with the input given.
     */
    public static Event parseEvent(String input) throws DukeException {
        if (input.trim().equals("")) {
            throw new DukeException("Oops! Event cannot be empty");
        }
        String[] arr = input.split("/at");
        if (arr.length == 1 || arr[0].trim().equals("")) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        String detail = arr[0].trim();
        LocalDateTime date = parseDate(arr[1].trim());
        if (date == null) {
            throw new DukeException("Oops! Format of date and time might be wrong.");
        }
        return new Event(detail, date);
    }

    /**
     * Parse the number given by user.
     * @param input The number given by user in string.
     * @return An integer representing task number.
     */
    public static int parseNumber(String input) throws DukeException {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Oops! Please provide a number!");
        }
    }
}
