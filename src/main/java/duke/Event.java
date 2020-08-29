package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Event with specified time.
 */
public class Event extends Task {
    /** the time of the Event. */
    protected final String at;
    /** the time of the Event as java.time.LocalDate. */
    protected LocalDate date;

    /**
     * Constructor for Event.
     * @param description the description of the event.
     * @param at the time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException ex){
            this.date = null;
        }
    }

    /**
     * Constructor for Event.
     * @param description the description of the event.
     * @param isDone boolean to indicates whether the event has been done or not.
     * @param at the time of the event.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException ex){
            this.date = null;
        }
    }

    /**
     * Marks the Event as done.
     * @return Event with updated status (done).
     */
    @Override
    public Event completeTask() {
        return new Event(description, true, at);
    }

    /**
     * Gets the format of the Event to be saved in hard disk.
     * @return Event object in specified format.
     */
    @Override
    public String getData() {
        return "EVENT#" + description + "#" + String.valueOf(isDone) + "#" + at;
    }

    /**
     * Gets the string representation of the Event object.
     * @return the string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + (date != null
            ? (date.getDayOfWeek() + ", " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
            : at) + ")";
    }
}
