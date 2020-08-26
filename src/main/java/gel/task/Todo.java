package gel.task;

/**
 * A type of <code>Task</code>.
 */
public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
