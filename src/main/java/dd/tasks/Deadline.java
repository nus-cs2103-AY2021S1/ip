package dd.tasks;

public class Deadline extends Task {

    protected String by;

    /**
     * Class Constructor.
     *
     * @param description Description of deadline.
     * @param by Due date or date and time of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns due date of deadline.
     *
     * @return Due date of deadline.
     */
    public String getDate() {
        return by.substring(0, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "T , 1 , " + description + " , " + by;
        }
        else {
            return "T , 0 , " + description + " , " + by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
