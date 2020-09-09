package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description description of task.
     * @param isDone      whether or not a task has been done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes task completion symbol from boolean value
     *
     * @return String with symbol formatting
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //returns tick or X symbol accordingly
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String saveText();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
