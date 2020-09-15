package duke.task;

/**
 * Represents a task with a description and deadline.
 */
public class Deadline extends Task {
    /**
     * Constructs a new task with the specified description and deadline.
     *
     * @param description Description of task.
     * @param deadlineDate Deadline of task.
     */
    public Deadline(String description, String deadlineDate) {
        super(description, "[D]", deadlineDate);
    }

    /**
     * Returns a String representation of a deadline task.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
