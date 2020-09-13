package duke.task;

/**
 * Encapsulates a Task which contains information on whether the task is
 * completed and also the task name.
 */
public abstract class Task {
    private String taskName;
    private boolean isCompleted;
    private String type;

    /**
     * Constructs a new Task.
     */
    public Task(String type, String isCompleted, String taskName) {
        this.taskName = taskName;
        if (isCompleted.equals("0")) {
            this.isCompleted = false;
        } else {
            this.isCompleted = true;
        }
        this.type = type;
    }

    /**
     * Completes the current task by setting the boolean taskCompleted to true.
     *
     * @return all details regarding this task
     */
    public String completeTask() {
        this.isCompleted = true;
        return this.toString();
    }

    /**
     * Checks if task has been completed.
     *
     * @return boolean value denoting if the task has been completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Getter method for type of the task.
     *
     * @return Type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter method for date and time of the task.
     *
     * @return Date and time of the task.
     */
    public abstract String getDateAndTime();

    /**
     * Getter method for name of the task.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Overrides the Object toString() method.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
