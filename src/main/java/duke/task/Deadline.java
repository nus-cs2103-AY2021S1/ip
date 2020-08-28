package duke.task;

import duke.util.DukeDateTime;
import duke.storage.CsvToTask;

import java.util.Objects;

/**
 * A Task with a deadline
 */
public class Deadline extends Task {

    private DukeDateTime deadline;

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

    public void setDeadline(DukeDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toCsv() {
        return CsvToTask.DEADLINE + ","
                + this.isCompleted() + ","
                + this.getDescription() + ","
                + this.getDeadline();
    }

    @Override
    public String toString() {
        return "[D]"
                + "[" + this.isCompletedSymbol() + "]"
                + " " + this.getDescription()
                + " (by: " + deadline.pretty() + ")";
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
