package duke.tasks;

/**
 * Represents a task.
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Creates a Task instance that has not been done.
     *
     * @param task A string containing task details.
     */
    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void doTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String check = isDone ? "✓" : "✗";
        return String.format("[%s] %s", check, task);

    }

    /**
     * Returns a string in a custom format that represent the task to be saved in local file.
     *
     * @return A string in a custom format that represent the task to be saved.
     */
    public String saveToString() {
        int check = isDone ? 1 : 0;
        return String.format("%d | %s", check, task);
    }

    public String getTask() {
        return this.task;
    }
}
