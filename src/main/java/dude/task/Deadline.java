package dude.task;

import java.time.LocalDate;

/**
 * The class handles tasks with deadlines to meet.
 */

public class Deadline extends DatedTask {
    /**
     * Constructor for the Deadline class.
     *
     * @param description description of the task.
     * @param by date the task is to be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Another constructor for the Deadline class.
     *
     * @param description description of the task.
     * @param isDone boolean value to denote if a task is completed.
     * @param by date the task is to be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone, by);
    }

    /**
     * Returns a string representation of the task to be written into the data file.
     *
     * @return String formatted description.
     */
    @Override
    public String toSave() {
        return "D | " + super.toSave();
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return String formatted description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
