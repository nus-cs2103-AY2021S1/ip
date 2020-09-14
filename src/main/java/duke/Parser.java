package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The Parser class takes in inputs from the user and convert into commands.
 */
public class Parser {

    private static final List<String> DATE_FORMATS = Arrays.asList("uuuu/MM/dd HHmm", "u/M/d HHmm", "u-M-d HHmm");
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String SORT = "sort";
    private static final String HELP = "help";

    /** Returns the command based on user input. */
    public static Command parse(String input) throws DukeException {
        int i = input.trim().indexOf(' ');
        String command = input.trim().toLowerCase();
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
            return new FindCommand(detail);
        case SORT:
            return new SortCommand(detail);
        case HELP:
            return new HelpCommand();
        default:
            throw new DukeException("Oops! I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Parses the date given by user.
     *
     * @param dateString Date provided by user in the form of string.
     * @return Date in LocalDateTime.
     */
    public static LocalDateTime parseDate(String dateString) {
        for (String format : DATE_FORMATS) {
            try {
                return LocalDateTime.parse(
                        dateString,
                        DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT));
            } catch (DateTimeParseException e) {
                e.getMessage();
            }
        }
        return null;
    }

    /**
     * Returns the ToDo the user specified.
     *
     * @param todoDetail The todo details given by user.
     * @return A ToDo with the input given.
     */
    public static ToDo parseTodo(String todoDetail) throws DukeException {
        if (todoDetail.equals("")) {
            throw new DukeException("Oops! Todo cannot be empty");
        }
        return new ToDo(todoDetail);
    }

    /**
     * Returns the Deadline the user specified.
     *
     * @param deadlineDetail The deadline details given by user.
     * @return A Deadline with the input given.
     */
    public static Deadline parseDeadline(String deadlineDetail) throws DukeException {
        if (deadlineDetail.equals("")) {
            throw new DukeException("Oops! Deadline cannot be empty");
        }
        String[] arr = deadlineDetail.split("/by");
        String detail = arr[0].trim();
        if (arr.length == 1 || detail.equals("")) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }
        LocalDateTime date = parseDate(arr[1].trim());
        if (date == null) {
            throw new DukeException("Oops! Format of date and time might be wrong.");
        }
        return new Deadline(detail, date);
    }

    /**
     * Returns the Event the user specified.
     *
     * @param eventDetail The event details given by user.
     * @return A Event with the input given.
     */
    public static Event parseEvent(String eventDetail) throws DukeException {
        if (eventDetail.trim().equals("")) {
            throw new DukeException("Oops! Event cannot be empty");
        }

        String[] arr = eventDetail.split("/at");
        String detail = arr[0].trim();
        if (arr.length == 1 || detail.equals("")) {
            throw new DukeException("Oops! You need to include both detail and time.");
        }

        String startAndEndDates = arr[1].trim();
        String[] dates = startAndEndDates.split("to");
        if (dates.length == 1) {
            throw new DukeException("Oops! You need to include both start and end dates.");
        }

        String startDateString = dates[0].trim();
        LocalDateTime startDate = parseDate(startDateString);
        String endDateString = dates[1].trim();
        if (endDateString.length() <= 4) {
            endDateString = startDateString.substring(0, startDateString.indexOf(' ')) + " " + endDateString;
        }
        LocalDateTime endDate = parseDate(endDateString);
        if (startDate == null || endDate == null) {
            throw new DukeException("Oops! Format of date and time might be wrong.");
        } else if (startDate.compareTo(endDate) > 0) {
            throw new DukeException("Oops! Start date must be earlier than end date");
        }
        return new Event(detail, startDate, endDate);
    }

    /**
     * Parses the number given by user.
     *
     * @param number The number given by user in string.
     * @return An integer representing task number.
     */
    public static int parseNumber(String number) throws DukeException {
        try {
            return Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Oops! Please provide a number!");
        }
    }
}
