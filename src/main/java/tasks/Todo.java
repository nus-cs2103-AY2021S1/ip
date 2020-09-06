package tasks;

/**
 * Responsible for creating Todo tasks.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task.
     *
     * @param description A description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
