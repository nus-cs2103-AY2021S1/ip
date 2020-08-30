package duke;

/**
 * Represents a task that is to be done.
 * Task object stores task description and done status. A task object is marked as not done by default.
 * A task object is able to be set as done by calling setDone() method.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    /**
     * Returns Task object as a string.
     * This method takes no parameters and returns the Task object as a string
     * in the form "[<Done Status>] <Task Description>".
     * This method overrides the method from parent class.
     * @return String this returns the Task object as a string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + this.description;
    }

    /**
     * Returns Task object as a string.
     * This method takes no parameters and returns the Task object as a string
     * in the form "[<Done Status>] <Task Description>".
     * This method overrides the method from parent class.
     * @return String this returns the Task object as a string
     */
    public String toStringFileFormat() {
        return "[" + getStatusIcon() + "] "
                + this.description;
    }

    /**
     * Sets done status of Task object as done
     *
     * @return Nothing.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns icon associated with Task object's done status.
     *
     * @return String status icon representing done status of Task object
     */
    public String getStatusIcon() {
        return (isDone ? "Done" : "Not Done");
    }
}
