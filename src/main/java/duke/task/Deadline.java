package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * A Task with deadline.
     * @param name name of Task
     * @param isDone whether Task is done
     * @param deadline deadline of task in LocalDateTime
     */
    public Deadline(final String name, final boolean isDone, final LocalDateTime deadline) {
        super(name, isDone);
        assert deadline != null : "Deadline is null";
        this.deadline = deadline;
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = getIsDone() ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneSymbol, getName(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }

    /**
     * Compares two deadlines and check if they are the same
     * @param o object to compare equality
     * @return true if the deadlines are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline d = (Deadline) o;
        return getName().equals(d.getName()) && deadline.equals(d.deadline);
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", getIsDone() ? 1 : 0, getName(), deadline);
    }

}
