/**
 * Represents a deadline task in the list.
 */
public class Deadline extends Task {

    /**
     * Creates a deadline task.
     * @param description
     */
    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
