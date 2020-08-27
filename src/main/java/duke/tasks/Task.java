package duke.tasks;

/**
 * Parent class to define a task.
 */
public class Task {

    private String task;
    private boolean done;

    /**
     * Creates a Task with the given task name.
     * @param task Task name
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String mark = done ? "\u2713" : "\u2717";
        return String.format("[%s] %s", mark, task);
    }

    /**
     * Returns string representation of the Task to store in file.
     * @return string representation
     */
    public String fileString() {
        return String.format("%s|%s", task, done ? "1" : "0");
    }

    public boolean search(String key) {
        return this.task.contains(key);
    }
}
