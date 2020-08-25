package seedu.bob.data.task;

/**
 * Represents a task.
 */
public class Task {
    protected final String description;
    protected final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    /**
     * Generates symbol based on whether task is done.
     *
     * @return A String representing a tick or cross symbol.
     */
    protected String getStatusIcon() {
        //return tick or cross symbols
        // (only_exception_case : appears to be ? in ACTUAL.TXT after running runtest.bat)
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     *
     * @return A done task.
     */
    public Task markDone() {
        return new Task(getDescription(), true);
    }

    /**
     * Convert to string value of task to be stored as data.
     *
     * @return String to be stored in hard disk.
     */
    public String convertToStringData() {
        return checkIsDone()
                ? "T/1/" + getDescription()
                : "T/0/" + getDescription();
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }

}
