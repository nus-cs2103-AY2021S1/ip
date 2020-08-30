public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     * Deadline is returned in the format MMM dd yyyy h.mma e.g. Sep 04 2020 4.00pm
     *
     * @return deadline of the task
     */
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
