package dude.task;

import java.time.LocalDate;

/**
 * The class handles tasks that occurs on a specific date.
 */
public class Event extends DatedTask {
    /**
     * Constructor for the Event class.
     *
     * @param description description of the task.
     * @param at date the task is to be attended on.
     */
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    /**
     * Constructor for the Event class.
     *
     * @param description description of the task.
     * @param isDone boolean value to denote if a task is completed.
     * @param at date the task is to be attended on.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone, at);
    }

    /**
     * Returns a string representation of the task to be written into the data file.
     *
     * @return String formatted description.
     */
    @Override
    public String toSave() {
        return "E | " + super.toSave();
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return String formatted description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
