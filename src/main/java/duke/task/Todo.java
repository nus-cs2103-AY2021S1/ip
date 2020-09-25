package duke.task;

/**
 * Represents a simple Task without a deadline or time of occurrence.
 */
public class Todo extends Task {

    /**
     * Creates a simple Task which has not been marked as done.
     *
     * @param description Details of what has to be done in the simple Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a simple Task.
     *
     * @param description Details of what has to be done in the simple Task.
     * @param isDone Boolean value that indicates if the simple Task has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of the simple Task
     * to be displayed on the UI.
     *
     * @return String representation of the simple Task to
     * be displayed on the UI.
     */
    @Override
    public String toDisplayString() {
        return "[T]" + super.toDisplayString();
    }

    /**
     * Returns the String representation of the simple Task
     * to be displayed in the storage of the task list.
     *
     * @return String representation of the String representation
     * of the simple Task to be displayed in the storage of the task list.
     */
    @Override
    public String toSavedString() {
        return "T" + " | " + super.toSavedString();
    }

}
