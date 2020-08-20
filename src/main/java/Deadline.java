public class Deadline extends Task {
    protected String by;

    /**
     * Creates a type of task with a deadline
     *
     * @param description the content of the task
     * @param by          the deadline of this task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }


    /**
     * Overrides the toString method
     *
     * @return a custom task description
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.by + ")";
    }


    public String toCustomString() {
        return "D | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + by;
    }


}