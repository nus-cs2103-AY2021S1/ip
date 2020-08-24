package duke;

/**
 * Represents a task that does not have a date attached to it.
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getItemName();
    }
}
