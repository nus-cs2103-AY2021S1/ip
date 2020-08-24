package duke;

/**
 * Represents a task in the Duke program, which has a description and can be set as done.
 */
public class Task {

    protected String desc;
    protected boolean isDone;

    /**
     * Initializes a newly created Task with a description.
     * @param desc description of task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Initializes a newly created Task with a description and whether it is done.
     * @param desc description of task.
     * @param isDone whether task is done.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Sets this task as done.
     * @return task set as done.
     */
    public Task setDone() {
        Task doneTask = new Task(this.desc);
        doneTask.isDone = true;
        return doneTask;
    }

    /**
     * Formats the task to a string to write in a file.
     * @return formatted task.
     */
    public String formatTask() {
        return ("P | " + (isDone ? "V" : "X") + " | " + desc);
    }

    /**
     * Returns a string representation of the task.
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return ("[" + (isDone ? "V" : "X") + "] " + desc);
    }

    /**
     * Checks whether a given task equals this task.
     * @param o object to be compared with this task.
     * @return true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task c = (Task) o;
            return this.desc.equals(c.desc);
        } else {
            return false;
        }
    }
}
