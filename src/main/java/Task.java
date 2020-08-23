/**
 * Encapsulates a Task with a description.
 *
 * Task can be completed and will be mark as done. By default, it is initialised as incomplete.
 */
public abstract class Task implements PrintSummary {

    /**
     * description of the task
     */
    protected String description;
    /**
     * boolean flag indicating completion of the task
     */
    protected boolean isDone;

    /**
     * Creates a new incomplete Task object.
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a tick or cross String depending on whether the task is completed.
     * @return tick if the task is completed, a cross otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a String representation of the Task.
     * @return String representing the task, showing its status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the state of th:we Task, whether it is completed or not.
     * @return true if the task is completed, false otherwise
     */
    protected boolean isCompleted() {
        return isDone;
    }

    /**
     * Marks the task as complete.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a summary of the Task.
     * @return string summarising the object
     */
    public abstract String getSummary();



}
