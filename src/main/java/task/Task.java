package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;
    protected boolean isTagged;

    /**
     * Creates Task object.
     * @param description String description to describe Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = null;
        this.isTagged = false;
    }

    /**
     * Creates Task object using overloaded contructor to indicate whether Task is completed..
     * @param description String description to describe Task.
     * @param isDone Boolean to indicate completion status of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isTagged = false;
    }

    /**
     * Creates Task object using overloaded contructor to indicate whether Task is completed..
     * @param description String description to describe Task.
     * @param isDone Boolean to indicate completion status of Task.
     * @param tag String tag to tag Task.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
        this.isTagged = true;
    }

    /**
     * Returns the completion status of the task.
     * @return Boolean of completion status.
     */
    public String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? "\u2713" : "\u2717");
    }

    /**
     * Marks this task as done/completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets given tag for this task.
     */
    public void setTag(String tag) {
        this.tag = tag;
        this.isTagged = true;
    }

    /**
     * Returns description of this task and its completion status.
     * @return String that describes task.
     */
    @Override
    public String toString() {
        if (isTagged) {
            return "[" + this.getStatusIcon() + "] " + "[" + this.tag + "] " + this.description;
        } else {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
}
