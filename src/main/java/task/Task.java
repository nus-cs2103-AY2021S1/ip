package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task object.
     * @param description String description to describe Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates Task object using overloaded contructor to indicate whether Task is completed..
     * @param description String description to describe Task.
     * @param isDone Boolean to indicate completion status of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     * @return Boolean of completion status.
     */
    public String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? "\u2713" : "\u2717");
    }

    /**
     * Marks this task as done/completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of this task and its completion status.
     * @return String that describes task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
