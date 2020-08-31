package Tasks;

/**
 * Tasks.Todo is the most general type of Tasks.Task.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name, Type.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
