package duke;

/**
 * Encapsulates details of a duke.Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a task with a description of it.
     * @param description an explanation of what the task is about
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to instantiate a task with customised status.
     * @param description description of the task
     * @param done status of the task
     */
    Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Visualises the status of a task.
     * @return whether or not a task is done
     */
    String getStatusIcon() {
        return "["
            + (isDone
                ? "\u2713"
                : "\u2718" ) //return tick or X symbols
            + "]";
    }

    /**
     * Marks a task as 'done'.
     * @return a task with a status of 'done'
     */
    Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Provides the description of the task
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    String toStorageRepresentation() {
        return toString();
    }
}
