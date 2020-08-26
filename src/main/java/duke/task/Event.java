package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is sub-class of Task. Event is a task that have time when it will take place.
 */
public class Event extends Task {

    /**
     * Date as LocalDate.
     */
    protected LocalDate date;
    /**
     * Date as String.
     */
    protected String date2;

    /**
     * Constructs an Event.
     *
     * @param description Description of the Event
     * @param date Date of the Event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
        this.date2 = date;
    }

    /**
     * Gets the Date of this Event.
     *
     * @return Date of this Event in LocalDate form.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the information about this Event except its status (done or not done).
     *
     * @return a String array that contains symbol type for Event ("E"), description, and date.
     */
    @Override
    public String[] getInfo() {
        return new String[] {"E", description, date2};
    }

    /**
     * Returns String representation of this Event.
     *
     * @return String that represents this Event.
     */
    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " [E]" + super.toString() + " (at: " + dateString + ")";
    }
}
