/**
 * <p>The Task class defines the behaviour of a task.</p>
 */
public class Task {
    protected final String taskDescription;
    private boolean isDone;
    private TagList tagList;

    /**
     * Creates a Task object that has a description and status of whether the task is done.
     *
     * @param taskDescription A String that represents the description of the task.
     * @param isDone A boolean that shows the status of the task.
     */
    public Task(String taskDescription, boolean isDone, TagList tagList) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
        this.tagList = tagList;
    }

    /**
     * Serialises a task into a string format.
     * @return A String that represents the serialised task.
     */
    public String serialiseTask() {
        return "";
    }

    public void setTaskAsDone() {
        this.isDone = true;
    }

    public TagList getTagList() {
        return tagList;
    }

    /**
     * Returns a tick or cross symbol to indicate the task's status:
     * <li>If the task is done, returns a tick.</li>
     * <li>If the task is not done, returns a cross.</li>
     *
     * @return A String that represents the symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

}
