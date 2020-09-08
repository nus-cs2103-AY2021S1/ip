package duke.task;

/**
 * Represents a TodoTask.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new instance of a TodoTask.
     * @param description Description of task
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return getPriority().isEmpty()
            ? "[T][" + getStatusIcon() + "] " + description
            : "[T][" + getStatusIcon() + "] " + description + " Priority: " + getPriority();
    }
}
