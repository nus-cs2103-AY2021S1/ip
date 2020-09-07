package seedu.duke;

/**
 * Represents a task that the user has to do.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Initializes a task that is by default not done yet, and with a description.
     * @param description The description for this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Initializes a task with both the description, and whether it is done.
     * @param description The description for this task.
     * @param isDone Whether this task is completed.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Returns the description of the task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the tag of the task
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns a tick icon if this task is done, and a cross icon if this task is not yet done.
     * @return The Icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s",
                getStatusIcon(), this.tag.length() > 0 ? "[#" + this.tag + "]" : "", this.description);
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Adds a tag to task object
     */
    public void addTag(String tag) {
        this.tag = tag;
    }
}
