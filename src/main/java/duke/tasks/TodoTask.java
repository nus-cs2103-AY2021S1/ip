package duke.tasks;

/**
 * Represents a TodoTask that is not constrained by datetime.
 */
public class TodoTask extends Task {

    /**
     * Initializes a TodoTask.
     *
     * @param description The description of the TodoTask.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Fully initialize a TodoTask.
     *
     * @param description The description of the TodoTask.
     * @param isDone      Indicates whether the TodoTask has been done.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public TodoTask markAsDone() {
        return new TodoTask(description, true);
    }

    /**
     * Updates the current todoTask and returns the updated todoTask.
     *
     * @param updateString A string that represents the todoTask description.
     * @return The updated todoTask.
     */
    @Override
    public TodoTask update(String updateString) {
        return new TodoTask(updateString, isDone);
    }

    @Override
    public String getData() {
        return "T|" + super.getData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
