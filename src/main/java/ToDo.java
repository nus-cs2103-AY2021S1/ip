/**
 * Class that makes a Todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description .
     *
     * @param description The description of this event.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of this event for saving.
     *
     * @return The String representation of this event for saving.
     */
    @Override
    public String writeSaveFormat() {
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, description, hasTag ? tagName : "");
    }

    /**
     * Returns the String representation of this event for the user.
     *
     * @return The String representation of this event for the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
