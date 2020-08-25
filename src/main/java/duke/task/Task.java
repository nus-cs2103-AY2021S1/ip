package duke.task;

/**
 * Represents a task.
 */
public class Task {
    public static final String TICK_ICON = "\u2713";
    public static final String CROSS_ICON = "\u2718";
    protected String desciption;
    protected boolean isDone;

    public Task(String description) {
        this.desciption = description;
        this.isDone = false;
    }

    /**
     * Returns the icon of current task status.
     * @return Tick if task is done and cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? Task.TICK_ICON : Task.CROSS_ICON);
    }

    /**
     * Marks current instance of task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true when the description contains certain keyword, false otherwise.
     * @param keyword Search key.
     * @return True when contains the search key and false otherwise.
     */
    public boolean containsKeyWord(String keyword) {
        return this.desciption.contains(keyword);
    }

    /**
     * Returns the status of a task.
     * @return True if the task has done and false other wise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string to be stored in data file.
     * @return Formatted string to be used in data file.
     */
    public String toFileStringFormat() {
        return String.format("%d / %s",isDone ? 1 : 0,this.desciption);
    }

    /**
     * Returns whether the task has the exact description as this object.
     * @param task Task that user intend to add.
     * @return True if the description is the same, false otherwise.
     */
    public boolean isExactDescription(Task task) {
        return  this.desciption.equals(task.desciption);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(),this.desciption);
    }
}
