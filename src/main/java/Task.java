public abstract class Task {
    protected boolean isDone;
    protected String taskName;

    public Task(String taskName) {
        this(false, taskName);
    }

    public Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    /**
     * Mark a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "✓" : "✘", this.taskName);
    }

    public abstract String toSaveString();
}
