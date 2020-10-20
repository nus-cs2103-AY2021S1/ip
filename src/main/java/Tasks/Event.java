package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    /** The time that the event is at */
    private LocalDate at;

    /**
     * Constructs a new Event object.
     * @param taskName the description of the event
     * @param at the time of the event
     */
    public Event(String taskName, String at) {
        super(taskName);
        this.at = LocalDate.parse(at);
    }

    /**
     * Gets the date of the event in the format yyyy-mm-dd
     * @return the date of the event in the format yyyy-mm-dd
     */
    public LocalDate getAtDate() {
        return this.at;
    }

    /**
     * Overriden toString method for event class
     * @return the string representation for event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
