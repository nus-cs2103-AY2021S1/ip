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
     * Overrides toString method of Task class
     *
     * @return Custom description of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
