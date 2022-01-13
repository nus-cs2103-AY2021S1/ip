package duke.task;

/**
 * Represents a to-do item that inherits from Task
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object with description.
     * @param description description of ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object with description and isDone.
     * @param description Description of ToDo.
     * @param isDone Boolean to set if ToDo is completed or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     * @return Todo as String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * @return Todo as String
     */
    @Override
    public String toStoredTextString() {
        return "T | " + super.toStoredTextString();
    }
}
