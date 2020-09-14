package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an event task with a time and date period.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    /**
     * Initializes an event task containing the task description and time and date of the event.
     *
     * @param description Description of the event task.
     * @param at          Event time and/or date information.
     */
    public Event(String description, String at) {
        super(description);

        this.atDateTime = tryParseDateTime(at);
        if (atDateTime == null) {
            this.atDate = tryParseDate(at);
        }
        this.at = at;
    }

    /**
     * Initializes an event task containing the task description, if the task is done and time and date of the event.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the event task.
     * @param isDone      If task is done.
     * @param at          Event time and/or date information.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.atDateTime = tryParseDateTime(at);
        if (atDateTime == null) {
            this.atDate = tryParseDate(at);
        }
        this.at = at;
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
     * Generates a string for printing from either the LocalDateTime object, LocalDate object, or at String.
     *
     * @return String representing the event time and/or date for printing.
     */
    public String generateAtFormat() {
        if (atDateTime != null) {
            return atDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        } else if (atDate != null) {
            return atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return at;
        }
    }

    /**
     * Returns the event time and/or date of the event.
     *
     * @return String representing the event time and/or date of the event.
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getInitial() + "]" + super.toString() + " (at: " + generateAtFormat() + ")";
    }
}
