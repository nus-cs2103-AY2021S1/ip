package chatterbox;

import chatterbox.task.Deadline;
import chatterbox.task.Event;
import chatterbox.task.Task;
import chatterbox.task.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Utility class for parsing date and time, as well as task inputs.
 */
public class Parser {
    private static final String[] dateFormats = new String[] {
            "d/M/y HHmm", "d-M-y HHmm", "d/M/y", "d-M-y"
    };

    /**
     * Tries to parse a raw string into a date and time, and returns a LocalDateTime if possible.
     *
     * @param dateTime  The raw string to be parsed into datetime.
     * @return  The string parsed as a LocalDateTime if possible, else null
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        for (String format : dateFormats) {
            try {
                Date d = new SimpleDateFormat(format).parse(dateTime);
                return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException ignored) {}
        }
        return null;
    }

    /**
     * Parses user input with task command to create a Task object.
     * Task can be a deadline, a todo, or an event.
     *
     * @param input Raw user input.
     * @return  Task object created based on user input.
     * @throws ChatterboxException  If description of the task command is empty or the command is invalid.
     */
    public static Task parseTask(String input) throws ChatterboxException {
        // Get first word of input
        String command = (input + " ").split(" ")[0];

        if (!input.contains(" ") || input.substring(input.indexOf(' ')).strip().equals("")) {
            throw new ChatterboxException("The description of a " + command + " cannot be empty");
        }
        String contents = input.substring(input.indexOf(' ') + 1);
        switch (command) {
        case "deadline":
            return new Deadline(contents);
        case "todo":
            return new ToDo(contents);
        case "event":
            return new Event(contents);
        default:
            throw new ChatterboxException("Invalid command.");
        }
    }
}
