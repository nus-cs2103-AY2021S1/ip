package duke.task;

/**
 * Class to add a Deadline, which is a Task that requires a datetime.
 */
public class Deadline extends Task {
    protected String deadlineDate;

    /**
     * Creates a Deadline.
     *
     * @param description Description of the Deadline.
     * @param deadlineDate DateTime that the Deadline is due.
     */
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Reformat the Deadline from existing data.
     *
     * @return Reformatted Deadline for writing to storage.
     */
    public String formatDeadline() {
        return String.format("D | %d | %s | %s",
                this.getIsDone() ? 1 : 0, this.getDescription(), this.deadlineDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }

}
