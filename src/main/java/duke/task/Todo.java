package duke.task;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Creates a new instance of a Todo object with attributes defined
     * in the parameters.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new instance of a Todo object with attributes defined
     * in the parameters.
     * Overloaded constructor which specifies the completion status of the task.
     * @param description Description of the todo.
     * @param isDone Completion status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the default toString() method in the Task class.
     * @return Returns a String describing the attributes of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
