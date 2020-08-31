package duke.task;

/**
 * Represents a Task object.
 */
public class Task {
    protected final String description;
    protected boolean isDone;
    protected final TaskType taskType;

    /**
     * Class constructor.
     *
     * @param description The description of Task.
     * @param taskType The type of Task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns a tick if the task is done.
     * Returns a cross otherwise.
     *
     * @return Tick if tasks is done, cross otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
//        return (isDone ? "✓" : "✘");
    }

    /**
     * Sets the isDone property to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the isDone property of the task.
     *
     * @return True if task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the type of task.
     * @return The type of task.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() + "] " + description;
    }
}