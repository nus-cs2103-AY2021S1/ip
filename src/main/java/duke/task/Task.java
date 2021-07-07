package duke.task;

public class Task {
    private String taskName;
    private boolean isCompleted;
    private String tagName;
    private boolean isTagged = false;

    /**
     * Constructs a task object.
     *
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Returns the task name.
     *
     * @return String indicating name of task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns true if the task is completed, false if not done yet.
     *
     * @return Boolean indicating progress of task.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns true if the task is tagged, false if not done yet.
     *
     * @return Boolean indicating tag of task.
     */
    public boolean isTagged() {
        return isTagged;
    }

    /**
     * Returns the tag name of the task.
     *
     * @return Tag name.
     */
    public String getTagName() {
        return this.tagName;
    }

    /**
     * Marks the task as done or completed.
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Adds tag to task.
     *
     * @param tag
     */
    public void addTag(String tag) {
        this.tagName = tag;
        this.isTagged = true;
    }
    /**
     * Returns the task in array form.
     *
     * @return String array.
     */
    public String[] taskToArray() {
        String done;
        if (isCompleted) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"Task", done, taskName};
        return str;
    }

    @Override
    public String toString () {
        if (isCompleted) {
            return "[\u2713] " + taskName;
        } else {
            return "[\u2718] " + taskName;
        }
    }
}
