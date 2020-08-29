package duke.task;

/**
 * {@code Task} is an abstract object which dictates the minimal structure every Task should have
 *      and capable of performing.
 */
public class Task { //TODO: convert to abstract class

    /** Description for this task. */
    protected String desc;
    /** The completion status of this task. */
    protected boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param desc The description for this task.
     */
    protected Task(String desc) {
        this(desc,false);
    }

    /**
     * Constructs a task object.
     *
     * @param desc The description for this task.
     * @param isDone The completion status of this task.
     */
    protected Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
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
     * Gets the export-style String for this task.
     *
     * @return Export-style string.
     */
    public String getSaveToFileString() {
        return String.format("%d`%s", (this.isDone) ? 1 : 0, this.desc);
    }


    /**
     * Returns the string output of this task for user's display.
     */
    @Override
    public String toString(){
        return String.format("[%c] %s", (this.isDone) ? '\u2713' : '\u2718', this.desc);
    }
}
