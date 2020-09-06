package chatterbox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import chatterbox.task.Deadline;
import chatterbox.task.Event;
import chatterbox.task.Task;
import chatterbox.task.ToDo;

/**
 * Utility class for parsing date and time, as well as task inputs.
 */
public class Parser {
    protected static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("MMM d yyyy HHmm'H'");
    private static final String[] dateFormats = new String[] {
        "d/M/y HHmm", "d-M-y HHmm", "d/M/y", "d-M-y"
    };

    /**
     * Tries to parse a raw string into a date and time, and returns a LocalDateTime if possible.
     * If parsing fails, this functions returns null.
     *
     * @param dateTime The raw string to be parsed into datetime.
     * @return The string parsed as a LocalDateTime if possible, else null
     */
    public static LocalDateTime parseDateTimeFromString(String dateTime) {
        for (String format : dateFormats) {
            try {
                Date d = new SimpleDateFormat(format).parse(dateTime);
                return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException e) {
                // Intentionally left empty
            }
        }
        return null;
    }

    /**
     * Combines the task input and the datetime to change the date display.
     * If datetime is null, then this function just returns the input string.
     *
     * @param input String to format.
     * @param dateTime LocalDateTime for the deadline task, can be null.
     * @return The datetime task string.
     */
    public static String getDateTimeTaskString(String input, LocalDateTime dateTime) {
        if (input.contains("/")) {
            String[] split = input.split("/", 2);
            String originalDateTime = split[1].substring(split[1].indexOf(' ') + 1);
            if (dateTime != null) {
                originalDateTime = dateTime.format(DF);
            }
            return String.format("%s(%s: %s)", split[0], split[1].split(" ")[0], originalDateTime);
        } else {
            return input;
        }
    }

    /**
     * Parses user input with task command to create a Task object.
     * Task can be a deadline, a todo, or an event.
     *
     * @param input Raw user input.
     * @return Task object created based on user input.
     * @throws ChatterboxException If description of the task command is empty or the command is invalid.
     */
    public static Task parseTask(String input) throws ChatterboxException {
        assert !input.strip().equals("");

        // Get first word of input
        String command = (input + " ").split(" ")[0];

        // Check if description of command is empty
        if (!input.contains(" ")) {
            throw new ChatterboxException("The description of a " + command + " cannot be empty");
        }

        // Return a Task based on the command word
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
