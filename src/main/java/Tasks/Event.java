package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task{

    /** The time that the event is at */
    protected LocalDate at;

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
     * Gets the date of the event in the format mmm d yyyy
     * @return the date of the event in the format mmm d yyyy
     */
    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Overriden toString method for event class
     * @return the string representation for event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
