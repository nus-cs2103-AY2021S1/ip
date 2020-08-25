package taskbot.task;

/**
 * Simulates a Task.
 */
public class TaskStub extends Task {
    /**
     * Creates a task.
     * @param task Description of task.
     */
    public TaskStub(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return getTask();
    }
}
