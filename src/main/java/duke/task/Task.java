package duke.task;

/**
 * Represents a task.
 */
public class Task {
    /**
     * The description of the task.
     */
    public String description;

    /**
     * The completion status of the task.
     */
    public boolean done;

    /**
     * Creates a new instance of a Task object with attributes defined
     * in the parameters.
     * @param description Description of the task.
     */
    Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Creates a new instance of a Task object with attributes defined
     * in the parameters.
     * Overloaded constructor which specifies the completion status of the task.
     * @param description Description of the task.
     * @param done Completion status of the task.
     */
    Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Retrieves the completion status of the task.
     * @return Returns the completion status.
     */
    public void done() {
        this.done = true;
    }

    /**
     * Overrides the default toString() method in the Object class.
     * @return Returns a String describing the attributes of the task.
     */
    @Override
    public String toString() {
        return this.done ? "[✓] " + this.description : "[✗] " + this.description;
    }
}
