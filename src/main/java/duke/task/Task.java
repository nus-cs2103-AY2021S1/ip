package duke.task;

/**
 * Represents a task. A {@code Task} object contains a content and a boolean which indicates whether the task has
 * been completed or not.
 */
public class Task {
    protected String content;
    protected boolean isDone;
    protected String tagName;

    /**
     * Constructor to create a new {@code Task} object.
     *
     * @param content
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
        this.tagName = "";
    }

    /**
     * Returns the string representation of the {@code Task} object which has been formatted to the correct
     * format as the file in which it will be saved into.
     *
     * @return a string representation of the {@code Task} object which has been formatted accordingly to the
     * file specification which the task will be saved into.
     */
    public String toDataFileFormat() {
        return String.format("? | %d | %s | %s", isDone ? 1 : 0, tagName, content);
    }

    /**
     * Marks the {@code Task} object as completed.
     *
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Sets the a tag to the {@code Task} object.
     *
     * @param tagName the description of the tag.
     */
    public void setTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * A getter method to return whether or not the {@code Task} object has been completed.
     *
     * @return a boolean value to indicate whether the {@code Task} object has been completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * A getter method to return the content of the {@code Task} object.
     *
     * @return the content of the {@code Task} object.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * A getter method to return the status icon of the {@code Task} object.
     *
     * @return the status icon of the {@code Task} object.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    /**
     * Returns the string representation of the {@code Task} object.
     *
     * @return the string representation of the {@code Task} object.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusIcon());
        if (!tagName.equals("")) {
            String tagDescription = "[" + this.tagName + "] ";
            stringBuilder.append(tagDescription);
        }
        stringBuilder.append(this.content);
        return stringBuilder.toString();
    }
}
