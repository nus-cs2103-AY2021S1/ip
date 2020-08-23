/**
 * This class encapsulates the functionality of a task
 */
abstract class Task {
    private String task;
    private boolean isDone;
    /**
     * Constructor for the class
     * @param task The task to be completed
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks this task as complete
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Getter for the task
     * @return The task to be completed
     */
    public String getTask() {
        return task;
    }

    /**
     * Getter to see if the task is complete
     * @return Whether the task is complete
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "✓" : "✗");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask() + "\n";
    }
}
