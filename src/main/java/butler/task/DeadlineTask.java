package butler.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDate deadline;

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
     * Returns a string representation of this deadline task.
     *
     * @return String representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + getDeadlineString() + ")";
    }

    /**
     * Returns a string representation of this task for storage in hard disk.
     *
     * @return String representation of this task for storage in hard disk.
     */
    @Override
    public String toStorageString() {
        return super.toStorageString()
                + " /by " + deadline;
    }

    /**
     * Returns a deep copy of this task.
     *
     * @return A deep copy of this task.
     */
    @Override
    public Task copy() {
        DeadlineTask task = new DeadlineTask(summary, deadline);
        task.isComplete = this.isComplete;
        return task;
    }
}
