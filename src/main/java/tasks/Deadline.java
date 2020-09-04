package tasks;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task with a name and a LocalDateTime object.
 */

public class Deadline extends TimedTask {

    /**
     * Creates a new Deadline task.
     * @param name Name of the task.
     * @param datetime Date and time of occurrence of the task.
     * @param done Whether the task is done or not.
     */
    public Deadline(String name, LocalDateTime datetime, boolean done) {
        super(name, done, datetime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String writeString() {
        return "D # " + super.writeString();
    }
}
