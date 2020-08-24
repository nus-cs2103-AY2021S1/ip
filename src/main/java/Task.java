public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object to represent a task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the status icon of the Task.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Update the Task as done.
     *
     * @return true when the Task is marked as done.
     */
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Return the task type of the Task.
     *
     * @return The task type of the Task.
     */
    public String getTaskType() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Return the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}