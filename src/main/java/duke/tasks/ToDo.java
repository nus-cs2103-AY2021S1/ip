package duke.tasks;

/**
 * Represents a task that needs to be done.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + super.toString();
    }
}
