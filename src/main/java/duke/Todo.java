package duke;

/**
 * Represents a task that does not have a date attached to it.
 */
public class Todo extends Task {

    private final String ICON = "[T]";

    /**
     * Class constructor.
     *
     * @param name Name of todo.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return ICON + super.getStatusIcon() + " " + super.getTaskName();
    }
}
