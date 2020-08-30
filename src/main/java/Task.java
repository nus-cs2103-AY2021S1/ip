public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true if the task has been completed and false otherwise.
     *
     * @return the completion status of the task
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    private String getStatusIcon() {
        // Returns either tick or X symbol
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
