package duke;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task { //added

    /**
     * Constructs a new instance of a ToDo object with description.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description, Type.TODO);
    }

    /**
     * Constructs a new instance of a ToDo object with description and completion status.
     *
     * @param description Description of task.
     * @param isDone Completion status.
     */
    public ToDo(String description, boolean isDone) {
        super(description, Type.TODO, isDone);
    }


    /**
     * Returns string representation of ToDo task.
     *
     * @return String representation of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
