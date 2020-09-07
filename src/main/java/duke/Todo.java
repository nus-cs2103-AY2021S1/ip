package duke;

/**
 * A type of task which contains description.
 */
public class Todo extends Task {
    /**
     * Constructor of Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representtaiton of  Todo in string.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
