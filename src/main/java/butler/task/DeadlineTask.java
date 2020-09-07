package butler.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;

    /**
     * Constructs a deadline task with the given <code>summary</code>
     * and <code>deadline</code>.
     *
     * @param summary Summary of this task.
     * @param deadline Deadline for this task.
     */
    public DeadlineTask(String summary, LocalDate deadline) {
        super(summary);
        this.deadline = deadline;
        this.taskType = TaskType.DEADLINE;
    }

    private String getDeadlineString() {
        return deadline.getDayOfMonth() + " "
                + deadline.getMonth().toString() + " "
                + deadline.getYear();
    }

    /**
     * Gets the deadline of this deadline task.
     *
     * @return Deadline of this deadline task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return String representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineString() + ")";
    }
}