/**
 * Represents a todo task that has a description, and a state of whether it has been done.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task.
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T " + super.toSave();
    }
}
