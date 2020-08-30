package seedu.duke;

import java.time.LocalDateTime;

/**
 * Class that represents the tasks the user can add to their list.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean status) {
        this.name = name;
        this.isDone = status;
    }

    /**
     * Returns what the current task is and its status.
     * @return String representing the task.
     */
    public String getStatus() {
        return this.isDone? ("[\u2713] " + toString()) : ("[\u2718] " + toString());
    }

    /**
     * Checks if the current task has been marked as done.
     */
    public void checkTask() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the current task name.
     * @return String representing the name of the task.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the type of the task.
     * @return String representing the type of task.
     */
    public String getType() {
        return "";
    }

    /**
     * Returns the deadline of the task, if any.
     * @return DateTime object that is the deadline of the task, if any.
     */
    public LocalDateTime getTime() {
        return null;
    }

    /**
     * Returns the state of the current task.
     * @return Boolean representing the current state of the task.
     */
    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     * @return String representing the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
