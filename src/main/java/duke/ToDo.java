package duke;

/**
 * Represents an event.
 */
public class ToDo extends Task {

    /**
     * Creates an instance of a ToDo task.
     * @param description  Contents of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }
    
    /**
     * Returns String representation of a todo task.
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}
