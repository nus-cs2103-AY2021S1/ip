package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Initializes a todo object.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}