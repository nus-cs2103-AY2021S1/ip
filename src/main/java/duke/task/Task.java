package duke.task;

/**
 * Tasks that the user input
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String tagDescription;

    /**
     * Creates a new Task object.
     *
     * @param description details about the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagDescription = "NIL";
    }

    /**
     * Creates a new Task object and set its isDone boolean.
     *
     * @param description details about the Task.
     * @param isDone whether Task is done or not.
     * @return Task with a corresponding description and completed status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tagDescription = "NIL";
    }

    /**
     * Creates a new Task object and set its isDone boolean and its tag.
     *
     * @param description details about the Task.
     * @param isDone whether Task is done or not.
     * @param tagDescription description of tag for this task.
     * @return Task with a corresponding description and completed status.
     */
    public Task(String description, boolean isDone, String tagDescription) {
        this.description = description;
        this.isDone = isDone;
        this.tagDescription = tagDescription;
    }

    /**
     * Returns either ✓ or ✘, depending on whether task has been done.
     *
     * @return ✓ or ✘.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract boolean hasKeyword(String keyword);

    public void setTag(String tagDescription) { this.tagDescription = tagDescription; }

    /**
     * Returns task description and its status icon.
     *
     * @return string containing its description and its status icon.
     */
    public String toString() {
        if (tagDescription.equals("NIL")) {
            return "[" + this.getStatusIcon() + "] " + this.description;
        } else {
            return "[" + this.getStatusIcon() + "] " + this.description + " #" + this.tagDescription;
        }
    }

    /**
     * Returns task description and its isDone status for saving.
     *
     * @return string containing its description and its status icon.
     */
    public abstract String infoString();
}
