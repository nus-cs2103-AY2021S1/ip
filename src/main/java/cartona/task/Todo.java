package cartona.task;

/**
 * The Todo class is a basic subclass of a Task.
 *
 * @author Jaya Rengam
 */
public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
