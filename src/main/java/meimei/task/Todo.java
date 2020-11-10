package meimei.task;

/**
 * Represents a task to be done. Inherits from Task.
 */
public class Todo extends Task {

    /**
     * Public constructor.
     *
     * @param taskName Name of the task as given by user.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
