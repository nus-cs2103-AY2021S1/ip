package duke.task;

/**
 * A simple type of task
 */
public class Todo extends Task {
    /**
     * Initializes a todo using the given description
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the todo
     * @return the String representation of the todo
     */
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the String representation of the todo when it is stored in a data file
     * @return the String representation of the todo when it is stored in a data file
     */
    @Override
    public String toStorageString() {
        if (super.isDone) return "T | 1 | " + description;
        else return "T | 0 | " + description;
    }
}