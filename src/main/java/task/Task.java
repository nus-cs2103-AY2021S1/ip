package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> object.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a <code>Task</code> object knowing whether the task is already done or not.
     * @param description The description of the task
     * @param isDone Whether the task is already done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        String tick = "\u2713";
        String cross = "\u2718";
        return (isDone ? tick : cross); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String simplifiedTaskString() {
        return this.getTaskType() + " - " + (this.isDone ? "1" : "0") + " - " + this.getDescription();
    }
}
