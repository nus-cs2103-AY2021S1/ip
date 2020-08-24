package taskbot.task;

public class Deadline extends Task {
    private String by;

    /**
     * Creates an incomplete deadline task
     * @param task Description of task
     * @param by Time to complete by
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    /**
     * Creates a deadline task
     * @param task Description of task
     * @param by Time to complete by
     */
    public Deadline(String task, String by, boolean isDone) {
        super(task, isDone);
        this.by = by;
    }

    /**
     * Gets the time of the deadline.
     * @return The time of the deadline.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Deadline: " + super.getTask() +
                "(by: " + by + ")" + "\n";
    }
}
