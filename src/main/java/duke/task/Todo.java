package duke.task;

/**
 * Represent a basic Task that the user has to complete.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public Todo markCompleted() {
        return new Todo(description, true);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", symbol, super.toString());
    }
}
