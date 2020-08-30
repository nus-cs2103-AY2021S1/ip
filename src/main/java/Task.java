/**
 * represents a task that has a description and a state of whether it has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * creates a new task with the state of not being done.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * gets the status icon for this task.
     * @return tick if it has been done and cross if it has not been done yet.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * returns the description of the task.
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] ", getStatusIcon()) + description;
    }

    /**
     * converts the task to string to store in the file
     * @return string to write into the storage file
     */
    public String toSave() {
        return (isDone? "1 " : "0 ") + description;
    }
}
