package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** The date and time the task is at. */
    private final LocalDateTime at;

    /**
     * Creates an Event task.
     *
     * @param description Details of the Event task.
     * @param at Date and time of the Event.
     */
    public Event (String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    /**
     * Method for writing the task to text format.
     *
     * @return Text format to be saved.
     */
    public String toWrite() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "[E]" + super.toString() + " /at " + at.format(formatter);
    }
}
