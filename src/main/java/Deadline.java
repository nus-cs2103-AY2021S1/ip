/**
 * Represents a deadline task in the list.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Creates a deadline task.
     * @param description
     * @param time
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + time + ")";
    }
}
