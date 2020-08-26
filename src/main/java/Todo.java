/**
 * Represents a Todo by its taskName and whether or not it has been completed.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-08-26
 */
public class Todo extends Task {
    Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
