package duke.task;

/**
 * Represents a task with description only.
 */
public class ToDo extends Task {

    /**
     * Constructs a <code>ToDo</code> Object to represent a todo.
     *
     * @param description The description of a todo item.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts a todo into serialized form (e.g.
     * 'T | 1 | 1 | read book').
     */
    @Override
    public String serialize() {
        return String.format("T | %d | %d | %s", getStatusCode(), getPriorityValue(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
