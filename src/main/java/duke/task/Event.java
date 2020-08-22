package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /**
     * The date associated with the Event.
     */
    LocalDateTime date;

    /**
     * Creates a new instance of an Event object with attributes defined
     * in the parameters.
     * @param description Description of the event.
     * @param date Date that the event is on.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

    }

    /**
     * Creates a new instance of an Event object with attributes defined
     * in the parameters.
     * Overloaded constructor which specifies the completion status of the task.
     * @param description Description of the event.
     * @param date Date that the event is on.
     * @param done Completion status of the event.
     */
    public Event(String description, String date, boolean done) {
        super(description, done);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Retrieves the date of the event.
     * @return Returns the date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Overrides the default toString() method in the Task class.
     * @return Returns a String describing the attributes of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
