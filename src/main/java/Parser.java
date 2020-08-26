import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    
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
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    
    public static int parseDone(String s) {
        int index = Integer.parseInt(s.substring(5));
        return index - 1;
    }

    public static Event parseEvent(String display) throws DukeException {
        if (display.length() == 5 || display.length() == 6) { // "event" or "event "
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (display.contains("/")) {
            int index = display.indexOf("/");
            if (display.substring(index + 1).equals("")
                    || display.substring(index + 3).equals("")
                    || display.substring(index + 4).equals("")) {
                throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
            }
            String description = display.substring(6, index - 1);
            String eventDate = display.substring(index + 4);
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

    public static int parseDelete(String s) throws DukeException {
        if (s.length() == 6 || s.length() == 7) {
            throw new DukeException("ERROR: Specify the task number which you want to delete.");
        }
        int index = Integer.parseInt(s.substring(7));
        return index - 1;
    }

    public static Todo parseTodo(String s) throws DukeException {
        if (s.length() == 4 || s.length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = s.substring(5);
        return new Todo(description);
    }

    public static Deadline parseDeadline(String s) throws DukeException {
        if (s.length() == 8 || s.length() == 9) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (s.contains("/")) {
            int index = s.indexOf("/");
            if (s.substring(index + 1).equals("")
                    || s.substring(index + 3).equals("")
                    || s.substring(index + 4).equals("")) {
                throw new DukeException("☹ OOPS!!! Please key in a valid date and time.");
            }
            String description = s.substring(9, index - 1);
            String deadlineDate = s.substring(index + 4);
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
}
