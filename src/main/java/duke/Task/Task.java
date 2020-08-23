package duke.Task;

public abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    protected Task(String task) {
        this.taskDescription = task;
        this.isDone = false;
    }

    /**
     * Returns the description of task
     * @return string representation of the description of task
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns true if object is completed, false otherwise
     * @return true if object is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Marks task as completed
     */
    public void completeTask() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + (this.isDone ? "\u2713" : "\u2717") + "] " + this.taskDescription;
    }
}
