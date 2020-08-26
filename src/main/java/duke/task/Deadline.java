package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * A Task with deadline.
     * @param name name of Task
     * @param done whether Task is done
     * @param deadline deadline of task in LocalDateTime
     */
    public Deadline(final String name, final boolean done, final LocalDateTime deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneSymbol, getName(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", isDone() ? 1 : 0, getName(), deadline);
    }

}
