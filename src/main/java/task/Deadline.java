package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task
 */
public class Deadline extends Task {
    private LocalDateTime deadlineBy;

    public Deadline(String description, LocalDateTime deadlineBy, boolean isDone) {
        super(description, isDone);

        assert deadlineBy != null : "Timing cannot be null";
        this.deadlineBy = deadlineBy;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Date and time of the deadline.
     */
    public LocalDateTime getTiming() {
        return this.deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineBy.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}