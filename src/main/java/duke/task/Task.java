package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Creates a new instance of a Task object with attributes defined
     * in the parameters.
     *
     * @param description Description of the task.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new instance of a Task object with attributes defined
     * in the parameters.
     * Overloaded constructor which specifies the completion status of the task.
     *
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return Returns the completion status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return Returns the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates a task's description.
     * @param description New description of the event.
     */
    public void updateDesc(String description) {
        this.description = description;
    }

    /**
     * Overrides the default toString() method in the Object class.
     *
     * @return Returns a String describing the attributes of the task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[✓] " + this.description : "[✗] " + this.description;
    }
}
