package Task;

/**
 * Represents a to do task without a date.
 */
public class Todo extends Task {

    /**
     * @param description Task.Task description.
     */
    public Todo(String description) {
        super(description, null);
    }

    /**
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
