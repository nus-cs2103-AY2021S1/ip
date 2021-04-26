package duke.task;

/**
 * An abstract class to represent the tasks stored and modified in the chatbot.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Task constructor.
     *
     * @param description Details of the task.
     * @param isDone      Progress of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Display the progress of the task.
     *
     * @return A boolean to check the task's progress.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    /**
     * Marking the task as done.
     */
    public void done() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
