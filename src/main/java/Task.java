package main.java;

/**
 * Encapsulates a Task.
 */
public class Task {
    /** A brief description of the task. */
    protected String description;

    /** Tracks the completion of the task */
    protected boolean isDone;

    /**
     * Constructs a task that has not been completed with a description.
     *
     * @param description a brief description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task, which may or may not have been completed, with a description.
     *
     * @param isDone indicates if the task has been completed.
     * @param description a brief description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * Returns tick symbol when task is indicated as done.
     * Returns X symbol when task is not indicated as done.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that the task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the object is equal or not equal to the task respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task task = (Task) o;
            return this.description.equals(task.description) && this.isDone == task.isDone;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of task, which includes the status icon
     * and description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    public String saveFormat() {
        if (isDone) {
            return "T | 1 | " + this.getDescription();
        } else {
            return "T | 0 | " + this.getDescription();
        }
    }
}

