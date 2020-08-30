package duke.task;

/**
 * Represents an abstract task that can be added into a task list.
 */
public abstract class Task {

    /** Stores the description of the Task. */
    private String name;

    /** Stores the done status of the Task. */
    private boolean done;

    /**
     * Creates a task containing the description of the task.
     * Automatically initialises the initial done status as false.
     *
     * @param name Description of the task.
     */
    Task(String name) {
        this.name = name;
        done = false;
    }

    /**
     * Creates a task containing the description and done status of the task.
     * Initialises the initial done status based on input.
     *
     * @param name Description of the task.
     * @param done Done status of the task.
     */
    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the task as done by setting the done status to true.
     */
    public void markDone() {
        done = true;
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the done status of the task.
     *
     * @return The done status of the task.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the string representation of an abstract task to be appended to a local file.
     *
     * @return String representation of a task to be appended to a local file.
     */
    public abstract String appendFile();

    /**
     * Returns the string representation of an abstract task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String doneString = (done == true ? "✓" : "✗");
        return "[" + doneString + "] " + name;
    }
}
