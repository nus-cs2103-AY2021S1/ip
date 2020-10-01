package duke.task;

/**
 * Represents a Todo item in Duke.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskIdentifier() {
        return "T";
    }
}
