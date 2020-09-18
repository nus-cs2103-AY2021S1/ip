package sparrow.data.task;

/**
 * Represents a Todo.
 */
public class Todo extends Task {
    /**
     * Creates a Todo.
     * @param description Details of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
