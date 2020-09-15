package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Task object.
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
