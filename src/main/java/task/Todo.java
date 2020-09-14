package task;

/**
 * Represents a Todo object by its taskName and whether or not it has been completed.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-08-26
 */
public class Todo extends Task {
    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
