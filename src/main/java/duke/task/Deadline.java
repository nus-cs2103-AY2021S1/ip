package duke.task;

/**
 * Represents a task with a description and deadline.
 */
public class Deadline extends Task {
    private String deadlineDate;

    /**
     * Constructs a new task with the specified description and deadline.
     *
     * @param description Description of task.
     * @param deadlineDate Deadline of task.
     */
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns a String representation of a deadline task.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }
}
