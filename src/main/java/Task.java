/**
 * A class of task.
 * Contains a description of task and a boolean to indicate the status of task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     * @param description a description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloading constructor.
     * @param description a description of a task.
     * @param isDone a boolean show the status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**\
     * Returns the icon for corresponding status of task.
     * @return sign of tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns immutable task with done status.
     * @return new task with done status.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Returns a string of task in format of task in data file.
     * @return string of a task.
     */
    public String stringify() {
        return this.toString();
    }

    /**
     * Returns task in string.
     * @return task in string.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
