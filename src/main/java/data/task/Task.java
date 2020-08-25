package data.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task in the task list.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task. "O" for completed task, "X" for uncompleted task.
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string of the task to be added into the local storage file.
     * @return The string of the task in the local storage file
     */
    public abstract String fileFormat();

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", this.getStatusIcon(), this.description);
    }

}

