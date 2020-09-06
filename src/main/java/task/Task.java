package task;

/**
 * The Task class implements methods that will be used
 * by its child class to create various task object.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with empty string as  its
     * description and false for isDone.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Constructs a new Task with task description specified
     * by user command.
     *
     * @param description To define task description.
     */
    public Task(String description) {
        assert !(description.isEmpty()) : "Task Description is empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task's description.
     *
     * @return String task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the current task description to the specified
     * description.
     *
     * @param description The task's description/
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns whether a task is finished or not.
     *
     * @return boolean Returns true if task is done,
     * false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status (done/undone) of the task
     * in symbolic form ([O]/[X]).
     *
     * @return String Returns "[O]" if the task is done, and
     * return "[X]" otherwise.
     */
    private String getStatus() {
        return isDone ? "[O]" : "[X]";
    }


    /**
     * Sets the current isDone to the specified boolean.
     *
     * @param isDone Sets isDone to true if task
     * has finished, and false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns messages to user. The result is the concatenation of :
     * 1. The status of the Task
     * 2. The description of the task.
     *
     * @return String Returns the task type ([T]]), its status as well
     * as its description.
     */
    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }
}
