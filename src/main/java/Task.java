public class Task {
    /** Description of the task. */
    protected String description;

    /** Completion status of the task */
    protected boolean isDone;

    /**
     * Construct new Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick or cross symbols depending on completion.
     *
     * @return Tick or cross unicode
     */
    public char getStatusIcon() {
        return (isDone ? '\u2713' : '\u2717');
    }

    /**
     * Sets the isDone attribute to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns part of the task string to be written to the duke.txt storage file.
     *
     * @return task string.
     */
    public String toStorageString() {
        return "[" + (isDone ? "1" : "0") + "] " + description;
    }
}
