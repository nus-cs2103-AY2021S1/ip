import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a parser object which scans user input.
 */
public class Parser {

    /**
     * Returns a command depending on the input string.
     * @param s the string.
     * @return Command which specifies the action taken.
     * @throws DukeException unidentified strings.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static Command parse(String s) throws DukeException {
        assert s.length() > 0 : "input should not be empty";
        if (s.equals("list")) {
            return new ListCommand();
        } else if (s.equals("bye")) {
            return new ExitCommand();
        } else if (s.startsWith("done")) {
            int index = parseDone(s);
            return new DoneCommand(index);
        } else if (s.startsWith("delete")) {
            int index = parseDelete(s);
            return new DeleteCommand(index);
        } else if (s.startsWith("todo")) {
            Todo todo = parseTodo(s);
            return new AddCommand(todo);
        } else if (s.startsWith("event")) {
            Event event = parseEvent(s);
            return new AddCommand(event);
        } else if (s.startsWith("deadline")) {
            Deadline deadline = parseDeadline(s);
            return new AddCommand(deadline);
        } else if (s.startsWith("find")) {
            return new FindCommand(parseFind(s));
        } else if (s.equals("sort")) {
            return new SortCommand();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns an integer specifying the index
     * of the task to be marked as done.
     *
     * @param s the string input starting with "done".
     * @return an integer which represents the index of the task.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static int parseDone(String s) throws DukeException {
        final int DONE_WITH_SPACING = 5;
        final String ERROR_MESSAGE = "ERROR: wrong format for done command";
        if (s.startsWith("done ") && s.length() > DONE_WITH_SPACING) {
            try {
                int index = Integer.parseInt(s.substring(DONE_WITH_SPACING));
                return index - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(ERROR_MESSAGE);
            }
        } else {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns an integer representing the index
     * of the task to be deleted from taskList.
     *
     * @param s the string input starting with "delete".
     * @return an integer.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static int parseDelete(String s) throws DukeException {
        final int DELETE_WITH_SPACING = 7;
        final String ERROR_MESSAGE = "ERROR: wrong format for delete command";
        if (s.startsWith("delete ") && s.length() > DELETE_WITH_SPACING) {
            try {
                int index = Integer.parseInt(s.substring(DELETE_WITH_SPACING));
                return index - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(ERROR_MESSAGE);
            }
        } else {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns the input string to be used to find matching tasks.
     *
     * @param s the string input starting with "find".
     * @return the user input string.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static String parseFind(String s) throws DukeException {
        final int FIND_WITH_SPACING = 5;
        if (s.startsWith("find ") && s.length() > FIND_WITH_SPACING) {
            return s.substring(FIND_WITH_SPACING);
        } else {
            final String ERROR_MESSAGE = "ERROR: wrong format for find command";
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns a todo to be added into taskList.
     *
     * @param s the string input starting with "todo".
     * @return a todo object.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static Todo parseTodo(String s) throws DukeException {
        final int TODO_WITH_SPACING = 5;
        final String TODO = "todo";
        if (s.equals(TODO) || s.equals(TODO + " ")) {
            final String EMPTY_DESCRIPTION_ERROR_MESSAGE =
                    "ERROR: The description of a todo cannot be empty.";
            throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }
        if (s.startsWith(TODO + " ") && s.length() > TODO_WITH_SPACING) {
            return new Todo(s.substring(TODO_WITH_SPACING));
        } else {
            final String ERROR_MESSAGE = "ERROR: wrong format for todo command";
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns a deadline to be added into taskList.
     *
     * @param s the string input starting with "deadline".
     * @return a deadline.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static Deadline parseDeadline(String s) throws DukeException {
        final int DEADLINE_WITH_SPACING = 9;
        final String DEADLINE = "deadline";
        final String EMPTY_DESCRIPTION_ERROR_MESSAGE =
                "ERROR: The description of a deadline cannot be empty.";
        if (s.equals(DEADLINE) || s.equals(DEADLINE + " ")) {
            throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }
        final String BY = " /by ";
        if (s.startsWith(DEADLINE + " ") && s.length() > DEADLINE_WITH_SPACING && s.contains(BY)) {
            final String SLASH = "/";
            int index = s.indexOf(SLASH);
            String trimmedDescription = s.substring(DEADLINE_WITH_SPACING, index).trim();
            if (trimmedDescription.length() == 0) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            final int DEADLINE_DATE_INDEX = index + 4;
            if (s.substring(DEADLINE_DATE_INDEX).equals("")) {
                final String DATE_ERROR_MESSAGE = "ERROR: deadline missing date.";
                throw new DukeException(DATE_ERROR_MESSAGE);
            }
            String description = s.substring(DEADLINE_WITH_SPACING, index - 1);
            String deadlineDate = s.substring(DEADLINE_DATE_INDEX);
            try {
                LocalDate date = LocalDate.parse(deadlineDate);
                return new Deadline(description, date);
            } catch (DateTimeParseException e) {
                final String DATE_FORMAT_MESSAGE = "deadline date must be in the format YYYY-MM-DD";
                throw new DukeException(DATE_FORMAT_MESSAGE);
            }
        } else {
            final String ERROR_MESSAGE = "ERROR: wrong format for deadline command";
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns an event to be added into taskList.
     *
     * @param s the string input starting with "event".
     * @return an event object.
     * @throws DukeException throws an exception when the format is wrong.
     */
    public static Event parseEvent(String s) throws DukeException {
        final int EVENT_WITH_SPACING = 6;
        final String EVENT = "event";
        final String EMPTY_DESCRIPTION_ERROR_MESSAGE =
                "ERROR: The description of an event cannot be empty.";
        if (s.equals(EVENT) || s.equals(EVENT + " ")) {
            throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }
        final String AT = " /at ";
        if (s.startsWith(EVENT + " ") && s.length() > EVENT_WITH_SPACING && s.contains(AT)) {
            final String SLASH = "/";
            int index = s.indexOf(SLASH);
            String trimmedDescription = s.substring(EVENT_WITH_SPACING, index).trim();
            if (trimmedDescription.length() == 0) {
                throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            final int EVENT_DATE_INDEX = index + 4;
            if (s.substring(EVENT_DATE_INDEX).equals("")) {
                final String DATE_ERROR_MESSAGE = "ERROR: event missing date.";
                throw new DukeException(DATE_ERROR_MESSAGE);
            }
            String description = s.substring(EVENT_WITH_SPACING, index - 1);
            String eventDate = s.substring(EVENT_DATE_INDEX);
            try {
                LocalDate date = LocalDate.parse(eventDate);
                return new Event(description, date);
            } catch (DateTimeParseException e) {
                final String DATE_FORMAT_MESSAGE = "event date must be in the format YYYY-MM-DD";
                throw new DukeException(DATE_FORMAT_MESSAGE);
            }
        } else {
            final String ERROR_MESSAGE = "ERROR: wrong format for event command";
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
