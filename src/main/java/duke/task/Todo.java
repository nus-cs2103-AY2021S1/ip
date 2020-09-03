package duke.task;

import java.util.Optional;

/**
 * This is a Todo Task
 * It keeps a description of a Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo Task.
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the letter T for writing to hard disk file.
     */
    @Override
    public String getStringType() {
        return "T";
    }

    /**
     * Returns empty Optional to tell Storage to leave date segment blank.
     */
    @Override
    public Optional<String> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
