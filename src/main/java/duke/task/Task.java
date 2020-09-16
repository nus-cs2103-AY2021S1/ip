package duke.task;

import java.time.LocalDateTime;

/**
 * The base task class for other tasks class to inherit from.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Initial setup for all child classes of Task class.
     * @param description The description about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this instance of the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    public abstract String getTaskType();

    public abstract String getDate();

    public abstract LocalDateTime getActualDate();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getStatusIcon(), description);
    }

}
