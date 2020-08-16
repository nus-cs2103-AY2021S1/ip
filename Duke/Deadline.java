package Duke;
/**
 * The Deadline class that represents a Deadline task.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Deadline extends Task {
    private String dueDate;
    public Deadline(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDate + ")";
    }
}
