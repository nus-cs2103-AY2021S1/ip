package duke.task;

/**
 * Represents a Task which can be added to or deleted from a task
 * list, or marked as done.
 */
public class Task {

    /** Details of what has to be done in the Task */
    protected String description;

    /** Boolean value that indicates if the Task has been completed */
    protected boolean isDone;

    /**
     * Creates a Task which has not been marked as done.
     *
     * @param description Details of what has to be done in the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param description Details of what has to be done in the Task.
     * @param isDone Boolean value that indicates if the Task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the state of completion of a Task as a status icon.
     *
     * @return A tick symbol if the Task is done, or an X otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Returns the details of what has to be done in the Task.
     *
     * @return Details of what has to be done in the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the completion status of the Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the String representation of the Task to be displayed on the UI.
     *
     * @return String representation of the Task to be displayed on the UI.
     */
    public String toDisplayString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the String representation of the Task to be saved in
     * the storage of the task list.
     *
     * @return String representation of the Task to be saved in the
     * storage of the task list.
     */
    public String toSavedString() {
        int isDoneInt = isDone ? 1 : 0;
        return isDoneInt + " | " + getDescription();
    }

}
