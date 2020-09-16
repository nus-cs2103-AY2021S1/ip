package duke.task;

/**
 * A task that need to be done by a certain deadline.
 * Command syntax: deadline + description + /by + time
 * Example:        deadline CS2103T IP /by every Thursday
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor.
     * @param description The description of the task.
     * @param deadline The time of deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor with the addition of isDone.
     * @param isDone Check if the task is done.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Represent the task in an audience-friendly form.
     * Format:  [D][isDone] + description + (by: deadline)
     * Example: [D][x] CS2103T IP (by: every Thursday)
     */
    @Override
    public String getStatus() {
        return "[D]" + getIcon() + " " + getDescription()  + " (by: " + deadline + ")";
    }

    /**
     * Represent the task in a suitable form to store data.
     * Format:  D|description|isDone|deadline
     * Example: D|CS2103T IP|false|every Thursday
     */
    @Override
    public String getDataFormat() {
        return "D" + "|" + getDescription() + "|" + getIsDone() + "|" + deadline;
    }
}
