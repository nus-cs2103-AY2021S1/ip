/**
 * <p>The Task class defines the behaviour of a task.</p>
 */
public class Task {
    protected final String taskDescription;
    private boolean isDone;

    /**
     * Creates a Task object that has a description and status of whether the task is done.
     * @param taskDescription A String that represents the description of the task
     * @param isDone A boolean that shows the status of the task
     */
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void setTaskAsDone()
    {
        this.isDone = true;
    }

    /**
     * Returns a tick or cross symbol to indicate the task's status:
     * <li>If the task is done, returns a tick.</li>
     * <li>If the task is not done, returns a cross.</li>
     * @return A String that represents the symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }
}