package duke.task;

/**
 * This is a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Initializes a Task object.
     *
     * @param description A string describing
     *                    the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
