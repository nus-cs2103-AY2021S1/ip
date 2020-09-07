package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class which extends from the Task class.
 * User can add a Event with specific date and time using this class.
 */
public class Event extends Task {
    protected LocalDateTime date;

    /**
     * Constructor for event task.
     *
     * @param description for event task.
     * @param date due for the event.
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Method call for a standardise way of storing the Event task.
     *
     * @return data of the event which can be read in the Storage Class.
     */
    @Override
    public String getState() {
        return "E|" + (this.isDone ? "1" : "0") + "|"
                + this.description + "|"
                + this.date.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: "
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a")) + ")";
    }
}
