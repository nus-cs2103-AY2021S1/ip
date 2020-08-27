/**
 * Class used for deadline tasks.
 */
public class Deadline extends Task {

    /**
     *  String used to store deadline.
     */
    protected String by;

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param by Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for deadline class.
     *
     * @param description Task description.
     * @param by Deadline.
     * @param isDone Describes if task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns simple string format for file.
     *
     * @return Simple string description.
     */
    @Override
    public String toStringSimple() {
        return "D | " + super.toStringSimple() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
