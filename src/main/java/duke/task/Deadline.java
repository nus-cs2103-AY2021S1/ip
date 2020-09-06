package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a type of task with a deadline.
     *
     * @param description the content of the task.
     * @param by          time in yyyy-mm-dd.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = convertToLocalDate(by);
    }


    /**
     * Creates tasks which could have been completed.
     *
     * @param description the content of the task.
     * @param isDone      the status of the task.
     * @param by          the deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = convertToLocalDate(by);
    }


    /**
     * Overrides the toString method.
     *
     * @return a custom task description.
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: "
                + convertToString(this.by) + ")";
    }


    /**
     * Returns a fixed format in string to store the task to storage.
     *
     * @return D | 0 | return book | June 6th.
     */
    public String toCustomString() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }


}
