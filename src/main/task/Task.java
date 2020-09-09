package main.task;

/**
 * Represents tasks.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructs a Task instance with the name of the task.
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Constructs a Task instance with the name of the task
     * and the done state of the task.
     * @param name the name of the task.
     * @param doneState the done state of the task.
     */
    public Task(String name, boolean doneState) {
        this.name = name;
        this.isDone = doneState;
    }

    /**
     * Gets the name of the task.
     * @return the name of the task.
     */
    public String getName() {
        return name;
    }

    private String doneTag() {
        return isDone ? "[\u2713]" : "[\u2718]";
    }

    /**
     * Sets the done state of the task to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the string meant for writing to disk.
     * @return the string meant for writing to disk.
     */
    public String write() {
        return String.format(",%d,%s\n", isDone ? 1 : 0, name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", doneTag(), name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task o = (Task) obj;
            boolean isSameName = name.equals(o.name);
            boolean isSameDoneState = isDone == o.isDone;

            return isSameName && isSameDoneState;
        }
        return false;
    }
}
