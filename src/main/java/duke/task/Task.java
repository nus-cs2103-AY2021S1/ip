package duke.task;

/**
 * Represents a task in the Duke program, which has a description and can be set as done.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initializes a newly created Task with a description.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a newly created Task with a description and whether it is done.
     *
     * @param description description of task.
     * @param isDone whether task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of this task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets this task as done.
     *
     * @return task set as done.
     */
    public Task setAsDone() {
        Task doneTask = new Task(this.description);
        doneTask.isDone = true;
        return doneTask;
    }

    /**
     * Formats the task to a string to write in a file.
     *
     * @return formatted task.
     */
    public String formatTask() {
        return ("P | " + (isDone ? "V" : "X") + " | " + description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return ("[" + (isDone ? "V" : "X") + "] " + description);
    }

    /**
     * Checks whether a given task equals this task.
     *
     * @param o object to be compared with this task.
     * @return true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task c = (Task) o;
            return this.description.equals(c.description);
        } else {
            return false;
        }
    }
}
