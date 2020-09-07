package duke;

/**
 * Represents a task.
 */
public class Task {
    /** the description of the task. */
    protected String description;
    /** boolean indicating whether the task is done or not. */
    protected boolean isDone;
    /** the tag for the task. */
    protected String tag;

    /**
     * Constructor for Task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Constructor for Task.
     * @param description the description of the task.
     * @param isDone boolean to indicates whether the task has been done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tag = "";
    }

    /**
     * Gets the description of the task.
     * @return task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the tag of the task.
     * @return task tag.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Marks the Task as done.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Attaches tag to a task.
     * @param tag tag to be attached.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the tag(s) attached to the task.
     * @return tag(s) attached to the task.
     */
    public String tagToString() {
        String[] splittedTags = tag.split(",");
        StringBuilder tags = new StringBuilder("");
        for (String tag : splittedTags) {
            if (tag.trim() != "") {
                tags.append("#" + tag.trim() + " ");
            }
        }
        return tags.toString();
    }

    /**
     * Gets the status icon of the task.
     * @return a check icon if the task is done, a cross icon otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[V]" : "[X]"); //return tick or X symbols
    }

    /**
     * Gets the format of the Task to be saved in hard disk.
     * @return Task object in specified format.
     */
    public String getData() {
        return "TASK#" + description + "#" + String.valueOf(isDone) + "#" + tag;
    }

    /**
     * Gets the string representation of the Task object.
     * @return the string representation of the Task.
     */
    public String toString() {
        return getStatusIcon() + ' ' + tagToString() + description;
    }
}
