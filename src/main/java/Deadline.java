public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline task description
     * @param by          Deadline for task.
     **/
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getInitial() + "]" + super.toString() + " (by:" + by + ")";
    }
}
