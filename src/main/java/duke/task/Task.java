package duke.task;

/**
 * Wrapper class for all types of tasks.
 */
public class Task {

    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected String date;

    /**
     * Creates a brand new {@code Task}.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
