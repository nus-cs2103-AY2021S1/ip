/**
 * Represents a deadline. A deadline has both a description of the
 * activity which must be completed, and a date/time indicating when it
 * is due.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Returns a Deadline.
     * @param description Description of the Deadline.
     * @param by Date/time that the Deadline is due.
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
     * Returns a String representation of the Deadline that will be
     * saved to the hard disk.
     * @return String representation of the Deadline.
     */
    public String getFormattedString() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | "
                + by;
    }
}
