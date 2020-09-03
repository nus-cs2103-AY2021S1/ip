package duke.task;

/**
 * Class that simulates the task that user has inputted.
 */

public class Task {
    private static final String TASK_MARKED_BEFORE = "This task has already been marked!";
    private static final String TASK_MARKED = "Nice! I've marked this task as done:";
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object the containing details of the task.
     *
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task object the containing details of the task.
     *
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     */
    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Displays a cross if task is not done, a tick otherwise.
     *
     * @return Byte encoding strings of the symbols tick or X.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2714" : "\u2718");
    }

    /**
     * Marks the task as completed.
     */
    public String markAsDone() {
        if (this.isDone) {
            return TASK_MARKED_BEFORE;
        } else {
            this.isDone = true;
            return TASK_MARKED + "\n" + this.toString();
        }
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     *
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format(",%s,%d\n", description, getTaskStatus());
    }

    /**
     * If a task is completed, return 1 else 0.
     *
     * @return An integer coded for the boolean status of the task.
     */
    private int getTaskStatus() {
        return isDone ? 1 : 0;
    }
    /**
     * Retrieves the details of the task.
     *
     * @return A string representing the details of the task.
     */
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
