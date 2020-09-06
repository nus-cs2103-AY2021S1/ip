package duke.task;

import duke.util.DateTime;

/**
 * Wrapper class for all types of tasks.
 */
public class Task {

    private static final String TICK_SYMBOL = "\u2713";
    private static final String CROSS_SYMBOL = "\u2718";

    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected DateTime dateTime;

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
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
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

    public DateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
