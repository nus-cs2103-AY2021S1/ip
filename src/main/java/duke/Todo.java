package duke;

/**
 * Encapsulates a Todo Task.
 */
public class Todo extends Task {
    /**
     * Instantiates Todo.
     *
     * @param description The description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates Todo.
     *
     * @param description The description of todo.
     * @param isDone Status completion of todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the toString() method.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
