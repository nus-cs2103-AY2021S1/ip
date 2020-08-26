package duke.task;

/**
 * Todo is an extension of a task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}