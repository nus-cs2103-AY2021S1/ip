package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    /**
     * Initializes a deadline task containing the task description and deadline of the task.
     *
     * @param description Description of the deadline task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        this.byDateTime = tryParseDateTime(by);
        if (byDateTime == null) {
            this.byDate = tryParseDate(by);
        }
        this.by = by;
    }

    /**
     * Initializes a deadline task containing the task description, if the task is done and deadline of the task.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the deadline task.
     * @param isDone      If task is done.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.byDateTime = tryParseDateTime(by);
        if (byDateTime == null) {
            this.byDate = tryParseDate(by);
        }
        this.by = by;
    }

    /**
     * Parses the date and time string, and returns a LocalDateTime object if the date and time string is in accordance
     * to any of the listed formats.
     * If date and time string does not follow any of the listed formats, null is returned.
     *
     * @param dateString String representing the date and time.
     * @return LocalDateTime object if parsing the successful.
     */
    public LocalDateTime tryParseDateTime(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd HHmm", "yyyy-MM-d HHmm", "dd/MM/yyyy HHmm",
                "dd/M/yyyy HHmm", "d/MM/yyyy HHmm", "d/M/yyyy HHmm", "dd-MM-yyyy HHmm", "dd-M-yyyy HHmm",
                "d-MM-yyyy HHmm", "d-M-yyyy HHmm");
        for (String formatString : formatStrings) {
            try {
                return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                //dateString will not be interpreted to contain a Date and Time
            }
        }
        return null;
    }

    /**
     * Parses the date string, and returns a LocalDate object if the date string is in accordance
     * to any of the listed formats.
     * If date string does not follow any of the listed formats, null is returned.
     *
     * @param dateString String representing the date.
     * @return LocalDate object if parsing the successful.
     */
    public LocalDate tryParseDate(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-d", "dd/MM/yyyy", "d/MM/yyyy",
                "dd/M/yyyy", "d/M/yyyy", "dd-MM-yyyy", "dd-M-yyyy", "d-MM-yyyy", "d-M-yyyy");
        for (String formatString : formatStrings) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                //dateString will not be interpreted to contain a Date
            }
        }
        return null;
    }

    /**
     * Generates a string for printing from either the LocalDateTime object, LocalDate object, or by String.
     *
     * @return String representing the deadline for printing.
     */
    public String generateByFormat() {
        if (byDateTime != null) {
            return byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        } else if (byDate != null) {
            return byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return by;
        }
    }

    /**
     * Returns the deadline of the task.
     *
     * @return String representing the deadline of the task.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getInitial() + "]" + super.toString() + " (by: " + generateByFormat() + ")";
    }
}
