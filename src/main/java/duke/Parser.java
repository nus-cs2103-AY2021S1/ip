package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to store methods for parsing
 */
public class Parser {
    private static final String[] dateFormats = new String[]{"yyyy-MM-dd", "dd-MM-yyyy", "yyyy/MM/dd", "dd/MM/yyyy",
        "MMM d yyyy", "d MMM yyyy"};

    /**
     * @param input the date to be parsed
     * @return a parsed date
     * @throws IllegalArgumentException exception if the date cannot be parsed to one of the pre-specified format
     */
    public static LocalDate parseDate(String input) throws IllegalArgumentException {
        for (String it : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(it);
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        throw new IllegalArgumentException("☹ OOPS!!! I can't recognize the date provided");
    }

    private static Todo parseTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return new Todo(desc);
    }

    private static Deadline parseDeadline(String desc) {
        desc = desc.trim();
        if (!desc.contains("/by")) {
            throw new IllegalArgumentException();
        }
        String[] elements = desc.split("/by");
        if (elements.length != 2) {
            throw new IllegalArgumentException();
        }
        String newDesc = elements[0].trim();
        String time = elements[1].trim();
        return new Deadline(newDesc, time);
    }

    private static Event parseEvent(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (desc.contains("/between")) {
            String[] elements = desc.split("/between");
            String[] times = elements[1].split("and");
            if (times.length != 2) {
                throw new IllegalArgumentException();
            }
            String newDesc = elements[0].trim();
            String happenAt = times[0].trim();
            String endAt = times[1].trim();
            return new Event(newDesc, happenAt, endAt);
        } else {
            String[] elements = desc.split("/at");
            if (elements.length != 2) {
                throw new IllegalArgumentException();
            }
            String newDesc = elements[0].trim();
            String time = elements[1].trim();
            return new Event(newDesc, time);
        }
    }

    /**
     * Parse the task from the raw input
     *
     * @param input the raw description about the task
     * @return a parsed task of 3 types Event, Todo or Deadline
     */
    public static Task parseTask(String input) {
        input.trim();
        try {
            if (input.startsWith("todo")) {
                return parseTodo(input.substring(4));
            } else if (input.startsWith("deadline")) {
                return parseDeadline(input.substring(8));
            } else if (input.startsWith("event")) {
                return parseEvent(input.substring(5));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
