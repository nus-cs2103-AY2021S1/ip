package duke.task;


/**
 * Represents a task.
 */
public class Task {
    private boolean isDone;
    private String taskContent;
    private String taskTag;

    /**
     * Creates a {@code Task} with given content.
     *
     * @param taskContent A String of content.
     */
    public Task(String taskContent) {
        this.taskContent = taskContent;
        this.isDone = false;
        this.taskTag = null;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks whether the task has been marked as done.
     * @return
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the task type.
     */
    public String getType() {
        return "";
    }

    /**
     * Gets the task content.
     */
    public String getContent() {
        return this.taskContent;
    }

    /**
     * Gets the task date.
     */
    public String getDate() {
        return "";
    }

    /**
     * Sets a tag for task.
     * @param tag is fetched from user command and stored as taskTag.
     * @return original tag if exists. Otherwise, it will return null.
     */
    public String setTag(String tag) {
        String oldTag = this.taskTag;
        if (tag.equals("null")) {
            this.taskTag = null;
        } else {
            this.taskTag = tag;
        }
        return oldTag;
    }

    /**
     * Gets the task tag.
     */
    public String getTag() {
        return this.taskTag;
    }

    @Override
    public String toString() {
        String res = "";
        if (isDone) {
            res += "[" + "\u2713" + "] " + this.taskContent;
        } else {
            res += "[" + "\u2718" + "] " + this.taskContent;
        }
        if (taskTag != null) {
            res += " " + taskTag;
        }
        return res;
    }
}
