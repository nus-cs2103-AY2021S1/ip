package duke.task;

/**
 * Represents a Task without Date and Time.
 */
public class Todo extends Task {
    /**
     * Class constructor.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the String representation of the Todo task.
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}