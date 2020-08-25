/**
 * Represents a Todo task which is a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object.
     * It is mainly for file writing.
     *
     * @param description is the description of the todo.
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * Creates a Todo object.
     * It is mainly for file reading.
     *
     * @param description is the description of the event.
     * @param isDone states if the Todo object is completed or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }
}
