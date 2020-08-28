package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a name and a LocalDateTime object.
 */

public class Event extends Task {
    protected LocalDateTime datetime;

    /**
     * Creates a new Event task.
     * @param name Name of the task.
     * @param datetime Data and time of occurrence of the task.
     * @param done Whether the task is done or not.
     */
    public Event(String name, LocalDateTime datetime, boolean done) {
        super(name, done);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + ")";
    }

    @Override
    public String writeString() {
        return "E # " + super.writeString() + " # " + datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
