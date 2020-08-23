/**
 * Todo is a type of task with a description.
 */
public class Todo extends Task {

    /**
     * Constructor of todo.
     *
     * @param description description of todo.
     */
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return String.format("%s || todo || %s", super.toSaveString(), this.description);
    }
}
