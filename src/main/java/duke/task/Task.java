package duke.task;

import java.time.LocalDateTime;

/**
 * <h1> Task Class </h1>
 * This is the parent task class that contains
 * the properties and methods that the various
 * types of child tasks requires.
 *
 * @author Lee Penn Han.
 * @version 1.0.
 * @since 2020-08-25.
 */
public class Task {
    protected String task;
    protected boolean done;
    protected LocalDateTime date;
    protected boolean isImportant;

    protected Task(String task) {
        this.task = task;
        this.done = false;
        this.isImportant = false;
    }

    /**
     * Returns the current task as a String.
     * @return String
     */
    public String getTask() {
        return this.task;
    }


    /**
     * Returns the status of whether the task is done.
     * @return Boolean
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns the date of the task
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Instantiates a Task object.
     * @param task the task to be done from User Input.
     * @return Task
     */
    public static Task setTask(String task) {
        return new Task(task);
    }

    /**
     * Changes the status of done from false to true to
     * indicate that a Task has been completed.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Changes the status of isImportance from false to true
     * to indicate that a Task is important
     */
    public void setAsImportant() {
        this.isImportant = true;
    }

    /**
     * Returns a boolean on whether a task is important
     * @return Boolean
     */
    public boolean isTaskImportant() {
        return this.isImportant;
    }
}
