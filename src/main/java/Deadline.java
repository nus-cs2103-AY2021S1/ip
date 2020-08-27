//package duke;
/**
 * Deadline <- Task
 */
public class Deadline extends Task {
    String description;
    String deadline;
    boolean isDone;

    /**
     * Init Deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Status in format [D][x] return book (by: 6 June)
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + deadline + ")";
    }

    /**
     * type of Task -> D for deadline
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * description to write to data file
     */
    @Override
    public String getDescription() {
        return super.getDescription() + "|" + this.deadline;
    }
}
