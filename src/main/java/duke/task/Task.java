package duke.task;

/**
 * Encapsulates a Task which contains information on whether the task is
 * completed and also the task name.
 */
public abstract class Task {
    private String taskName;
    private boolean taskCompleted;

    /**
     * Constructs a new Task.
     */
    public Task(String isCompleted, String taskName) {
        this.taskName = taskName;
        if (isCompleted.equals("0")) {
            this.taskCompleted = false;
        } else {
            this.taskCompleted = true;
        }
    }

    /**
     * Completes the current task by setting the boolean taskCompleted to true.
     *
     * @return all details regarding this task
     */
    public String completeTask() {
        this.taskCompleted = true;
        return this.toString();
    }

    /**
     * Overrides the Object toString() method.
     */
    @Override
    public String toString() {
        if (taskCompleted) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
