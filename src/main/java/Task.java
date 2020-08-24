package main.java;

/**
 * Holds the task object and relative operations.
 */
public class Task {

    /**
     * Stores status of the task, false for Not done; true for done
     */
    protected boolean status;

    /**
     * Stores the content of the task
     */
    protected String content;

    /**
     * Initializes the task with content.
     * Sets the status to false(not done).
     *
     * @param content Content of the task object.
     */
    public Task(String content) {
        this.status = false;
        this.content = content;
    }

    /**
     * Initializes the task with content and status.
     *
     * @param status  Status of the task.
     * @param content Content of the task object.
     */
    public Task(boolean status, String content) {
        this.status = status;
        this.content = content;
    }

    /**
     * Marks the task as done.
     */
    public void done() {
        this.status = true;
    }

    /**
     * Gives whether this task is done.
     *
     * @return Status of the task.
     */
    public boolean isDone() {
        return this.status;
    }

    /**
     * Gives the content of the task.
     *
     * @return Content of the task.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Gives the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (status ? "√" : "×") + "] " + content;
    }
}
