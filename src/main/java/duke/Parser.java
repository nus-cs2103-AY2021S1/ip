package duke;
import static java.lang.Integer.parseInt;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.*;

public class Parser {

    private static Command parseDone(String input) throws DukeException {
        try {
            String pattern = "(done\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(5);
                int index = parseInt(number) - 1;
                return new DoneCommand(index);
            } else {
                throw (new EmptyDescriptionException("done"));
            }
        } catch (NumberFormatException e) {
            throw (new TypeMismatchException("done"));
        } catch (DukeException e) {
            throw (e);
        }
    }

    private static Command handleToDo(String input) throws EmptyDescriptionException {
        String pattern = "(todo\\s)(.+)";
        if (input.trim().matches(pattern)) {
            String task = input.replaceAll(pattern, "$2");
            return new ToDoCommand(task);
        } else {
            throw (new EmptyDescriptionException("todo"));
        }
    }

    private static Command handleDeadline(String input) throws DukeException {
        String basePattern = "(deadline\\s)(.+)";
        String almostCompletePattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
        String dateTimePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d\\d\\d)";
        String completePattern = "(deadline\\s)(.+)\\s(/by\\s)" + dateTimePattern;
        String missingTaskPattern = "(deadline\\s/by)((\\s(.*))*)";
        try {
            if (!input.trim().matches(basePattern)) {
                throw (new EmptyDescriptionException("deadline"));
            } else if (input.trim().matches(missingTaskPattern)) {
                throw (new MissingTaskException());
            } else if (!input.trim().matches(almostCompletePattern)) {
                throw (new MissingTimeException("by"));
            } else if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                LocalDateTime dateTime = extractDateTime(input.replaceAll(completePattern, "$4"));
                return new DeadlineCommand(task, dateTime);
            } else {
                throw (new WrongDateTimeFormatException());
            }
        } catch (DukeException e) {
            throw (e);
        }
    }

    private static Command handleEvent(String input) throws DukeException {
        String basePattern = "(event\\s)(.+)";
        String almostCompletePattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
        String dateTimePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d\\d\\d)";
        String completePattern = "(event\\s)(.+)\\s(/at\\s)" + dateTimePattern;
        String missingTaskPattern = "(event\\s)(/at)((\\s(.*))*)";
        try {
            if (!input.trim().matches(basePattern)) {
                throw (new EmptyDescriptionException("event"));
            } else if (input.trim().matches(missingTaskPattern)) {
                throw (new MissingTaskException());
            } else if (!input.trim().matches(almostCompletePattern)) {
                throw (new MissingTimeException("at"));
            } else if (input.trim().matches(completePattern)) {
                String task = input.replaceAll(completePattern, "$2");
                LocalDateTime dateTime = extractDateTime(input.replaceAll(completePattern, "$4"));
                return new EventCommand(task, dateTime);
            } else {
                throw (new WrongDateTimeFormatException());
            }
        } catch (DukeException e) {
            throw (e);
        }
    }

    private static Command delete(String input) throws DukeException {
        try {
            String pattern = "(delete\\s)(.+)";
            if (input.trim().matches(pattern)) {
                String number = input.substring(7);
                int index = parseInt(number) - 1;
                return new DeleteCommand(index);
            } else {
                throw (new EmptyDescriptionException("delete"));
            }
        } catch (NumberFormatException e) {
            throw (new TypeMismatchException("delete"));
        } catch (IndexOutOfBoundsException e) {
            throw (new OutOfBoundsException());
        }
    }

    /**
     * Returns the LocalDateTime extracted from the input String.
     *
     * @param input The given input.
     * @return LocalDateTime of specified dateTime.
     * @throws InvalidDateTimeException If dateTime in input is invalid.
     */
    public static LocalDateTime extractDateTime(String input) throws InvalidDateTimeException {
        try {
            String pattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)\\s(\\d\\d)(\\d\\d)";
            String date = input.replaceAll(pattern, "$1");
            String hours = input.replaceAll(pattern, "$2");
            String minutes = input.replaceAll(pattern, "$3");
            String time = hours + ":" + minutes;
            return LocalDateTime.parse(date + "T" + time);
        } catch (DateTimeParseException e) {
            throw (new InvalidDateTimeException());
        }
    }

    private static Command handleDueIn(String input) throws DukeException {
        try {
            String basePattern = "(due in\\s)(.+)";
            String hourPattern = "(due in\\s)(\\d+)\\s(hours)";
            String dayPattern = "(due in\\s)(\\d+)\\s(days)";
            if (!input.trim().matches(basePattern)) {
                throw (new EmptyDescriptionException("due in"));
            } else if (input.trim().matches(hourPattern)) {
                long time = parseInt(input.replaceAll(hourPattern, "$2"));
                return new DueInCommand(time, true);
            } else if (input.trim().matches(dayPattern)) {
                long time = parseInt(input.replaceAll(dayPattern, "$2"));
                return new DueInCommand(time, false);
            } else {
                throw (new WrongDueInFormatException());
            }
        } catch (NumberFormatException e) {
            throw (new TypeMismatchException("due in"));
        } catch (IndexOutOfBoundsException e) {
            throw (new OutOfBoundsException());
        }
    }

    private static Command handleFind(String input) throws EmptyDescriptionException {
        String pattern = "(find\\s)(.+)";
        if (input.trim().matches(pattern)) {
            String keyword = input.replaceAll(pattern, "$2");
            return new FindCommand(keyword);
        } else {
            throw (new EmptyDescriptionException("find"));
        }
    }

    private static Command handleRepeat(String input) throws DukeException {
        String basePattern = "(repeat\\s)(.+)";
        String noRecurrencePattern = "repeat\\s(\\d+)";
        String noTaskPattern = "repeat\\s(\\D+)";
        String dateTimePattern = "(\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d\\d\\d)";
        String dayPattern = "(repeat\\s)(\\d+)\\s(\\d+)\\s(day\\suntil\\s)" + dateTimePattern;
        String weekPattern = "(repeat\\s)(\\d+)\\s(\\d+)\\s(week\\suntil\\s)" + dateTimePattern;
        String monthPattern = "(repeat\\s)(\\d+)\\s(\\d+)\\s(month\\suntil\\s)" + dateTimePattern;
        String yearPattern = "(repeat\\s)(\\d+)\\s(\\d+)\\s(year\\suntil\\s)" + dateTimePattern;
        if (!input.trim().matches(basePattern)) {
            throw new EmptyDescriptionException("repeat");
        } else if (input.trim().matches(noRecurrencePattern)) {
            throw new NoRecurrenceException();
        } else if (input.trim().matches(noTaskPattern)) {
            throw new MissingTaskIndexException();
        } else if (input.trim().matches(dayPattern)) {
            int index = parseInt(input.replaceAll(dayPattern, "$2"));
            long recurrence = parseInt(input.replaceAll(dayPattern, "$3"));
            LocalDateTime end = extractDateTime(input.replaceAll(dayPattern, "$5"));
            return new RepeatCommand(index, end, recurrence, RecurrenceType.DAY);
        } else if (input.trim().matches(weekPattern)) {
            int index = parseInt(input.replaceAll(weekPattern, "$2"));
            long recurrence = parseInt(input.replaceAll(weekPattern, "$3"));
            LocalDateTime end = extractDateTime(input.replaceAll(weekPattern, "$5"));
            return new RepeatCommand(index, end, recurrence, RecurrenceType.WEEK);
        } else if (input.trim().matches(monthPattern)) {
            int index = parseInt(input.replaceAll(monthPattern, "$2"));
            long recurrence = parseInt(input.replaceAll(monthPattern, "$3"));
            LocalDateTime end = extractDateTime(input.replaceAll(monthPattern, "$5"));
            return new RepeatCommand(index, end, recurrence, RecurrenceType.MONTH);
        } else if (input.trim().matches(yearPattern)) {
            int index = parseInt(input.replaceAll(yearPattern, "$2"));
            long recurrence = parseInt(input.replaceAll(yearPattern, "$3"));
            LocalDateTime end = extractDateTime(input.replaceAll(yearPattern, "$5"));
            return new RepeatCommand(index, end, recurrence, RecurrenceType.YEAR);
        } else {
            throw new WrongRecurrenceFormatException();
        }
    }

    /**
     * Returns a command to respond to the user input.
     *
     * @param input User input.
     * @return Command responding to the input.
     * @throws DukeException If user input is not recognized as a command.
     */
    public static Command parse(String input) throws DukeException {
        if (input.trim().equals("help")) {
            return new HelpCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseDone(input);
        } else if (input.startsWith("todo")) {
            return handleToDo(input);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(input);
        } else if (input.startsWith("event")) {
            return handleEvent(input);
        } else if (input.startsWith("delete")) {
            return delete(input);
        } else if (input.startsWith("due in")) {
            return handleDueIn(input);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("find")) {
            return handleFind(input);
        } else if (input.startsWith("repeat")) {
            return handleRepeat(input);
        } else if (input.startsWith("sort")) {
            return new SortCommand();
        } else {
            throw (new UnknownCommandException());
        }
    }
}
