package duke.core.task;

import duke.core.util.DukeDateTime;
import duke.core.storage.CsvToTask;

import java.util.Objects;

/**
 * A Task with a deadline
 */
public class Deadline extends Task {

    private DukeDateTime deadline;

    /**
     * Create an undone Task with a description and a deadline
     * @param description of task which is to be completed
     * @param deadline in which task should be completed by
     */
    public Deadline(String description, DukeDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Create an Task with a description and a deadline, and specifying whether the task has been completed
     * @param isCompleted A boolean to indicate the completion status of the task
     * @param description of task which is to be completed
     * @param deadline in which task should be completed by
     */
    public Deadline(boolean isCompleted, String description, DukeDateTime deadline) {
        super(isCompleted, description);
        this.deadline = deadline;
    }

    /**
     * @return The deadline in which the task should be completed by
     */
    public DukeDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * @param deadline The deadline in which the task should be completed by
     */
    public void setDeadline(DukeDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * @return A csv representation of this task.
     */
    @Override
    public String toCsv() {
        return CsvToTask.DEADLINE + ","
                + this.isCompleted() + ","
                + this.getDescription() + ","
                + this.getDeadline();
    }

    /**
     * @return A readable text representation of this task.
     */
    @Override
    public String toString() {
        return "[D]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription()
                + " (by: " + deadline.pretty() + ")";
    }

    /**
     * Two Deadlines are equal if they have the same description,
     * have the same completion status, and have the same deadline
     * @param obj The other object to be compared to
     * @return true if they are equivalent. Otherwise, false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Deadline deadline1 = (Deadline) obj;
        return getDeadline().equals(deadline1.getDeadline());
    }

    /**
     * @return A hash of the completion status, description, and deadline
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDeadline());
    }
}
