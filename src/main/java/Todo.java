/**
 * Represents a todo as a task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getData() {
        return "T | " + super.getData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
