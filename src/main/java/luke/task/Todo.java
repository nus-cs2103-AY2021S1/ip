package luke.task;

/**
 * Represents a task that the user has to finish.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object that indicates what the user needs to do.
     *
     * @param description details about the task
     */
    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toDataString() {
        return String.format("T|%s|", super.toDataString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
