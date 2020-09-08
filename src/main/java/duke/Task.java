package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description, which is marked incomplete by default.
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description.length() != 0;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task with the specified description and completion status.
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        assert description.length() != 0;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns unicode symbols (tick or cross) corresponding to the completion state of the task.
     * @return Tick if the task is complete, cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns an array of Strings representing the state of the task, to be passed to Storage to
     * be formatted and written to a file.
     * @return Array of Strings representing the current state of the Task.
     */
    public String[] serialize() {
        String[] output = new String[3];
        output[0] = this.isDone
                    ? "1"
                    : "0";
        output[1] = "Task";
        output[2] = description;

        return output;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
