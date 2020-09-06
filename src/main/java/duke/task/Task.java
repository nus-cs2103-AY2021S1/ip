package duke.task;

/**
 * Wrapper class for all types of tasks.
 */
public class Task {

    private static final String TICK_SYMBOL = "\u2713";
    private static final String CROSS_SYMBOL = "\u2718";

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
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL); //return tick or cross symbols
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
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
