package duke.task;

import java.util.Date;

import duke.util.DateFormatter;

/**
 * Models a deadline task.
 */
public class Deadline extends Task {
    protected Date by;

    /**
     * Constructs a Deadline.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the task for saving.
     *
     * @return String representation of the task for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0,
                this.description, DateFormatter.formatSave(this.by));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                DateFormatter.formatDisplay(this.by));
    }
}
