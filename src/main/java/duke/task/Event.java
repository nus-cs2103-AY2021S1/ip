package duke.task;

import exception.EventInvalidUsageException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an {@code Event} object. Inherits from {@code Task} object
 */
public class Event extends Task {
    protected LocalDate at;

    private Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Factory method for {@code Event} object
     *
     * @param event event description
     * @param at    event date in ISO-8601 format
     * @return the Event object
     * @throws EventInvalidUsageException on empty description or wrong date format
     */
    public static Event create(String event, String at) throws EventInvalidUsageException {
        if (event.equals("")) {
            throw new EventInvalidUsageException("Event description cannot be empty.");
        }

        try {
            return new Event(event, parseDate(at));
        } catch (DateTimeParseException ex) {
            throw new EventInvalidUsageException("Event date must be of the form yyyy-mm-dd.");
        }
    }

    private static LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    private static String showDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }

    /**
     * Show task's name and status
     *
     * @return a string that represents the task
     */
    @Override
    public String showTask() {
        return String.format("[%s]%s (at: %s)", this.getType(), super.showTask(), showDate(this.at));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.at;
    }

    public LocalDate getDate() {
        return this.at;
    }
}
