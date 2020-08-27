package duke.task;

import java.util.Optional;

/**
 * This is a Todo Task
 * It keeps a description of a Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo Task
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStringType() {
        return "T";
    }

    @Override
    public Optional<String> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
