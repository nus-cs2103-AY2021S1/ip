package duke.task;

import duke.util.DateTime;

/**
 * Class representing a deadline.
 */
public class Deadline extends Task {

    /**
     * Creates a brand new {@code Deadline}.
     * @param description Description of the deadline.
     * @param by Time that the deadline is due by.
     */
    public Deadline(String description, DateTime by) {
        super(description);
        this.dateTime = by;
        this.taskType = "D";
    }

    /**
     * Creates a {@code Deadline} from existing data.
     * @param isDone Deadline completion status.
     * @param description Description of the deadline.
     * @param by Time that the deadline is due by.
     */
    public Deadline(boolean isDone, String tags, String description, DateTime by) {
        this(description, by);

        if (isDone) {
            this.markDone();
        }

        this.addTagsFromData(tags);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDateTime());
    }
}
