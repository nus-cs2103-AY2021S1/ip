package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor to make a task object.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
