/**
 * <h1>Represents the most simple form of a task</h1>
 * Contains the specific details of a task and whether
 * the task has been completed.
 */
public class Task {
    private String task;
    private boolean isCompleted;

    /**
     * Creates a Task object.
     *
     * @param task
     * @param isCompleted
     */
    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the isCompleted flag as true once user completes the task.
     */
    public void completed() {
        isCompleted = true;
    }

    /**
     * An overriden method that returns a String with the details of a task.
     *
     * @return String containing a tick or a cross, if the task is complete or incomplete respectively,
     * together with the details of the task.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return "[\u2713] " + task;
        } else {
            return "[\u2718] " + task;
        }
    }
}
