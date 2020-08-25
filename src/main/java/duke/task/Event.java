package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@link Task} that happens on a particular date.
 */
public class Event extends Task {
    /**
     * The date on which the Event is happening.
     */
    private LocalDate date;

    /**
     * Instantiates a new Event object.
     * The new Event is not completed by default.
     * @param description The description of the Event.
     * @param date The date on which the Event is happening.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Instantiates a new Event object based on completion status provided.
     * @param description The description of the Event.
     * @param date The date on which the Event is happening.
     * @param completionStatus The completion status of the Event.
     */
    public Event(String description, LocalDate date, String completionStatus) {
        super(description, completionStatus);
        this.date = date;
    }

    /**
     * Overrides getType method in {@link Task}.
     * @return Type of Event.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Overrides getDate method in {@link Task}.
     * @return The date of the Event.
     */
    @Override
    LocalDate getDate() {
        return date;
    }

    /**
     * Returns a String representation of the Event.
     * Overrides toString in {@link Task}.
     * @return A String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
