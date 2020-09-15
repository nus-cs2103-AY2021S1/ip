package duke.task;

/**
 * Represents a specific task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        return "T | " + getDoneInteger() + " | " + description;
    }
}
