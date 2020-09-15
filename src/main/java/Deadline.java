import java.util.Date;

/**
 * Deadline is a type of Task but with a time limit.
 */
public class Deadline extends Task {

    private final Date by;

    /**
     * Creates a new Deadline.
     *
     * @param description Description of Deadline.
     * @param by          Due time of deadline.
     * @param isDone      True if completed, false if yet to be completed.
     */
    public Deadline(String description, Date by, boolean isDone, Priority priority) {
        super(description, TaskType.DEADLINE, isDone, priority);
        this.by = by;
    }

    @Override
    public String getSavedString() {
        return super.getSavedString() + " | " + Ui.formatDate(by);
    }

    @Override
    public boolean isOccuringOn(Date date) {
        return date.equals(by);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + Ui.formatDate(by) + ")" + (priority == null ? ""
                : " (priority: " + priority.toString() + ")");
    }
}
