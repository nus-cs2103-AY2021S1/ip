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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
