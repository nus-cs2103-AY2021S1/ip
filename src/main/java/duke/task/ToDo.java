package duke.task;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {
    /**
     * Initializes a task containing the task description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initializes a task containing the task description and if the task is done.
     * This is an overloaded constructor to allow for tasks in the hard drive to be loaded when Duke first runs.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getInitial() + "]" + super.toString();
    }
}
