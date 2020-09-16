package duke.task;

/**
 * A Task with a time-based deadline.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Initialise a deadline with a description and date of deadline.
     * @param description The description of this task.
     * @param deadline The deadline of this task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Initialise a deadline with a description and date of deadline
     * and whether it has been completed.
     * @param description The description of this task.
     * @param deadline The deadline of this task.
     * @param isDone Whether the deadline has been met.
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline + ")";
    }

    @Override
    public String getDescriptionForDatabase() {
        return "deadline - " + (isTaskDone() ? "1" : "0") + " - "
                + getDescription() + " - " + deadline;
    }
}
