import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    
    /**
     * Returns a command depending on the input string.
     * @param s the string.
     * @return Command which specifies the action taken.
     * @throws DukeException unidentified strings.
     */
    public static Command parse(String s) throws DukeException {
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
            // String input = parseFind(s);
            return new FindCommand(s);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns an integer specifying the index
     * of the task to be marked as done.
     * @param s the string input starting with "done".
     * @return an integer which represents the index of the task.
     */
    public static int parseDone(String s) {
        final int DONE = 5;
        int index = Integer.parseInt(s.substring(DONE));
        return index - 1;
    }

    /**
     * Returns an event to be added into taskList.
     * @param display the string input starting with "event".
     * @return an event.
     */
    public static Event parseEvent(String display) throws DukeException {
        final int EVENT = 5;
        final int EVENT_WITH_SPACING = 6;
        if (display.length() == EVENT || display.length() == EVENT_WITH_SPACING) { // "event" or "event "
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (display.contains("/")) {
            int index = display.indexOf("/");
            final int EVENT_DATE_AND_TIME = index + 4;
            if (display.substring(index + 1).equals("")
                    || display.substring(index + 3).equals("")
                    || display.substring(EVENT_DATE_AND_TIME).equals("")) {
                throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
            }
            String description = display.substring(EVENT_WITH_SPACING, index - 1);
            String eventDate = display.substring(EVENT_DATE_AND_TIME);
            try {
                LocalDate date = LocalDate.parse(eventDate);
                return new Event(description, date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please key in the date in the format YYYY-MM-DD");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The format is wrong. A dash is missing.");
        }
    }

    /**
     * Returns an integer representing the index
     * of the task to be deleted from taskList.
     * @param s the string input starting with "delete".
     * @return an integer.
     */
    public static int parseDelete(String s) throws DukeException {
        final int DELETE = 6;
        final int DELETE_WITH_SPACING = 7;
        if (s.length() == DELETE || s.length() == DELETE_WITH_SPACING) {
            throw new DukeException("ERROR: Specify the task number which you want to delete.");
        }
        int index = Integer.parseInt(s.substring(DELETE_WITH_SPACING));
        return index - 1;
    }

    /**
     * Returns a todo to be added into taskList.
     * @param s the string input starting with "todo".
     * @return a todo.
     */
    public static Todo parseTodo(String s) throws DukeException {
        final int TODO = 4;
        final int TODO_WITH_SPACING = 5;
        if (s.length() == TODO || s.length() == TODO_WITH_SPACING) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = s.substring(5);
        return new Todo(description);
    }
    
    /**
     * Returns a deadline to be added into taskList.
     * @param s the string input starting with "deadline".
     * @return a deadline.
     */
    public static Deadline parseDeadline(String s) throws DukeException {
        final int DEADLINE = 8;
        final int DEADLINE_WITH_SPACING = 9;
        if (s.length() == DEADLINE || s.length() == DEADLINE_WITH_SPACING) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (s.contains("/")) {
            int index = s.indexOf("/");
            final int DEADLINE_DATE_AND_TIME = index + 4;
            if (s.substring(index + 1).equals("")
                    || s.substring(index + 3).equals("")
                    || s.substring(DEADLINE_DATE_AND_TIME).equals("")) {
                throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
            }
            String description = s.substring(DEADLINE_WITH_SPACING, index - 1);
            String deadlineDate = s.substring(DEADLINE_DATE_AND_TIME);
            try {
                LocalDate date = LocalDate.parse(deadlineDate);
                return new Deadline(description, date);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please key in the date in the format YYYY-MM-DD");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The format is wrong. A dash is missing.");
        }
    }
    
    public static void parseFind(String s) {
        
        
    }
}
