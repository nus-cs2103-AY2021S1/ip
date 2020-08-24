/**
 * Encapsulates the todo task into a class.
 * The class supports initializing the todo task and
 * converting the todo task to a string.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
