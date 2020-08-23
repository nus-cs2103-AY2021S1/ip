package duke.task;

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

    @Override
    public String getData() {
        return "T|" + super.getData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}