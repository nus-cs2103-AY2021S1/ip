package duke.task;

/**
 * The Task class contains the name and completion
 * status of a task.
 */
public abstract class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Changes the completion status of the task to done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns the name of the Task object.
     *
     * @return A String which represents the name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the completion status of the Task object.
     *
     * @return A boolean which is true if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a string representation of a Task object.
     *
     * @return A String which contains the name and completion
     * status of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + name;
        } else {
            return "[\u2718] " + name;
        }
    }
}
