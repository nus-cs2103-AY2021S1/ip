package gel.task;

/**
 * A type of <code>Task</code>.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task.
     *
     * @param description Description of task.
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
