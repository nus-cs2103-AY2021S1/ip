package duke.tasks;

/** Represents the task that is stored in the list. */
public class Task {
    /** The indicator of whether the task is done. */
    private boolean isDone;
    /** The description of the task. */
    private String name;

    /** Constructor.
     *
     * @param name The description of the task.
     */
    public Task(String name) {
        isDone = false;
        this.name = name;
    }

    /** Constructor.
     *
     * @param isDone The indicator of whether the task is done.
     * @param name The description of the task.
     */
    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * @return The indicator of whether the task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * @return The description of the task.
     */
    public String getName() {
        return name;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /** Returns the string representation of the task. */
    public String toString() {
        return String.format("[%s] %s", isDone ? "O" : "X", name);
    }
}
