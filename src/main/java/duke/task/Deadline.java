package duke.task;

import duke.DukeDateTime;

import java.util.Objects;

/**
 * A Task with a deadline
 */
public class Deadline extends Task {

    private final DukeDateTime deadline;

    public Deadline(String description, DukeDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean isCompleted, String description, DukeDateTime deadline) {
        super(isCompleted, description);
        this.deadline = deadline;
    }
    
    public DukeDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.pretty() + ")";
    }

    /**
     * Get the csv representation of this task
     * @return A csv String representative of this task
     */
    @Override
    public String toCsv() {
        return TaskFactory.DEADLINE + "," + super.toCsv() + "," + deadline.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Deadline)) return false;
        if (!super.equals(obj)) return false;
        Deadline deadline1 = (Deadline) obj;
        return getDeadline().equals(deadline1.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDeadline());
    }
}
