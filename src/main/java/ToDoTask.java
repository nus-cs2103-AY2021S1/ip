/**
 * <p>The ToDoTask class defines the behavior of a todo task that only
 * has description and status.</p>
 */
public class ToDoTask extends Task {
    public ToDoTask(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        return "[T] [" + getStatusIcon() + "] " + taskDescription;
    }
}
