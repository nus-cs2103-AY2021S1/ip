package tasks;

import java.time.LocalDateTime;

/**
 * Represents an Event task with a name and a LocalDateTime object.
 */

public class Event extends TimedTask {

    /**
     * Creates a new Event task.
     * @param name Name of the task.
     * @param datetime Date and time of occurrence of the task.
     * @param done Whether the task is done or not.
     */
    public Event(String name, LocalDateTime datetime, boolean done) {
        super(name, done, datetime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String writeString() {
        return "E # " + super.writeString();
    }
}
