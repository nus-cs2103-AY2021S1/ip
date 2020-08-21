/**
 * Deadline task that inherits from Task class, and has an additional condition, which is when it is due by.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline object
     *
     * @param description details about the Deadline
     * @param by time/date the deadline is due by
     * @return Deadline with a corresponding description and sets it as uncompleted.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline object
     *
     * @param description details about the Deadline
     * @param isDone whether Deadline is done or not
     * @param by time/date the deadline is due by
     * @return Deadline with a corresponding description and completed status.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overrides toString method of Task class
     *
     * @return Custom description of the deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    @Override
    public String infoString() {
        String x = "0";
        if (isDone) {
            x = "1";
        }
        return "D | " + x +  " | " + this.description +  " | " + this.by;
    }
}
