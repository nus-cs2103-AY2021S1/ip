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
     * @return Tick or cross symbols
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    /**
     * Sets the isDone attribute to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Interprets a string of task and returns the corresponding Task object.
     *
     * @param taskString toString() version of a Task object in the saved file.
     * @return Task object corresponding to input task string.
     * @throws DukeException If Task format is invalid.
     */
    public static Task textToTask(String taskString) throws DukeException {
        return Parser.getTask(taskString);
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
