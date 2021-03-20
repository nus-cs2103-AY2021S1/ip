/**
 * Represents a deadline. A deadline is a task that has a description of the
 * activity to be completed, and a date or time indicating when it is due.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructs a Deadline with the specified description and date.
     * @param description Description of the Deadline.
     * @param by Date that the Deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a representation of the Deadline that will be saved to the hard disk.
     * @return String representation of the Deadline.
     */
    public String getFormattedString() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | "
                + by;
    }
}
