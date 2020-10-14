package juke.task;


/**
 * Represents a Task to be done by a given deadline date.
 */
public class Deadline extends Task {

    private TaskDate by;

    /**
     * Constructs a Deadline with an input description and by date.
     * @param description Description of Deadline task.
     * @param by Date by which task is to be completed.
     */
    public Deadline(String description, TaskDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the representative text of the Deadline.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "D";
        return String.format("%s/%s/%s",
                type,
                super.taskSaver(),
                by.saveDateToDisk());
    }

    /**
     * Sets the by date for Deadline.
     * @param by New date to change to.
     */
    public void setByDate(TaskDate by) {
        this.by = by;
    }

    /**
     * Outputs the Deadline as a String.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by + ")";
    }
}
