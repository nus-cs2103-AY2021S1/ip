package duckie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task type in charge of task containing Event date and time
 */
public class Event extends Task{
    protected final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
    protected LocalDateTime dateTime;

    /**
     * Instantiate a Event task
     * @param description Description of the task
     * @param dateTime Date and Time of the task
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Overrides method to return the type of the Event task
     * @return "E" string
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Overrides method to return the date and time of the Event task
     * @return Date and Time string of Event Task
     */
    @Override
    public String getDate() {
        return dtf.format(this.dateTime);
    }


    /**
     * Overrides method to return the String representation of a Event task
     * @return String representation of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dtf.format(this.dateTime) + ")";
    }
}
