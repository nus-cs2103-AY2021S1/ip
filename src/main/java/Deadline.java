/**
 * Represents a deadline task in the list.
 */
public class Deadline extends Task {

    /**
     * Creates a deadline task.
     *
     * @param description deadline description
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Creates a deadline task.
     *
     * @param description deadline description
     * @param isDone whether the deadline is done
     */
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
