package duke.task;

/**
 * Represents a task
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor without extra arguments.
     * @param description String representing description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Class constructor with extra boolean argument.
     * @param description String representing description of task.
     * @param isDone Boolean representing whether task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the status of task.
     * @return String representing tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Translates the task to an excel format.
     * @return String of entry to be written to a file.
     */
    public String write() {
        if (isDone) {
            return 1 + "," + description;
        }
        return 0 + "," + description;
    }
}
