public class Deadline extends Task {
    private String by;

    Deadline(String description, String by) {
        this(description, false, by);
    }

    Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Return updated task of subtype: Deadline
     *
     * @param isDone New status for the task
     * @return new Deadline with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return new Deadline(super.description, isDone, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}