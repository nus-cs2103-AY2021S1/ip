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

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getType() {
        return this.type;
    }

    public abstract String getDateAndTime();

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
