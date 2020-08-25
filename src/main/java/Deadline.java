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

    @Override
    public String getStoredString(){
        return "D" + super.toString() + " " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
