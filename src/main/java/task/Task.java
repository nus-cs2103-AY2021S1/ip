package task;

/**
 * Represents a generic Task by its taskName and whether or not it has been completed.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-08-26
 */
public class Task {
    protected String taskName;
    protected boolean isCompleted;

    /**
     * @param taskName Name of task.
     * @param isCompleted Whether task has been completed.
     */
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * @return Current task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * @return Current task's state of completion.
     */
    public boolean getStatus() {
        return this.isCompleted;
    }

    /**
     * Marks the current task as complete by changing its boolean attribute isCompleted.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the current task as uncompleted by changing its boolean attribute isCompleted.
     */
    public void markAsUncompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String task = "[";
        if (isCompleted) {
            task += "done";
        } else {
            task += "not done";
        }
        task += "] " + taskName;
        return task;
    }
}
