package duke.task;

/**
 * {@code Task} is an abstract class which dictates the minimal structure every Task should have
 *      and capable of performing.
 */
public abstract class Task {
    protected static final String TASK_DELIMITER = duke.util.Util.getTaskDelimiter();

    /** Description for this task. */
    protected String desc;
    /** The completion status of this task. */
    protected boolean isDone;
    /** The priority of this task. */
    protected Priority priority;

    /**
     * Constructs a task object with no priority.
     *
     * @param desc The description for this task.
     */
    protected Task(String desc) {
        this(desc,false, Priority.UNCLASSIFIED);
    }

    /**
     * Constructs a task object.
     *
     * @param desc The description for this task.
     * @param isDone The completion status of this task.
     * @param priority The priority for this task.
     */
    protected Task(String desc, boolean isDone, Priority priority) {
        this.desc = desc;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Marks this task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of this task.
     *
     * @return The description for this task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the priority for this task.
     *
     * @param priority Priority to be assigned
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Sets the priority for this task.
     *
     * @param priorityValue Priority to be assigned in integer form.
     */
    public void setPriority(int priorityValue) {
        this.priority = Priority.getPriority(priorityValue);
    }

    /**
     * Gets the priority type currently assigned to this task.
     *
     * @return Priority of this task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Gets the export-style String for this task.
     *
     * @return Export-style string.
     */
    public String getSaveToFileString() {
        return String.format("%d%s%d%s%s",
                (this.isDone) ? 1 : 0, TASK_DELIMITER,
                this.priority.getPriorityValue(), TASK_DELIMITER,
                this.desc);
    }

    /**
     * Returns the string output of this task for user's display.
     */
    @Override
    public String toString(){
        return String.format("[%c][%s] %s",
                (this.isDone) ? '\u2713' : '\u2718', this.getPriority(), this.desc);
    }
}
