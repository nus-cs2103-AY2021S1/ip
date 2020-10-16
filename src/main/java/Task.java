/**
 * Represents a task of the user. A task object will have its description,
 * completion status and task type stored.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String tag;

    /**
     * Constructor for a task.
     * @param description The detailed description of the task created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = null;
        this.tag = null;
    }

    /**
     * Constructor for a task.
     * @param description The detailed description of the task created.
     * @param isDone The completion status of the task.
     * @param type The type of the task.
     */
    public Task(String description, boolean isDone, String type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * A getter for icon that shows completion status of a task.
     * @return A tick for completed task, a cross for uncompleted task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getStatus() { return this.isDone;}

    /**
     * This method is used to mark a task as done.
     */
    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
        }
        assert(this.isDone) : "Mark down failed.";
    }

    /**
     * This method is used to mark a task as deleted.
     */
    public void markAsDeleted() {
        this.isDone = false;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * This method is used to set the type of a task.
     */
    public void setType(String type) {
        assert (type == "T" | type == "D" | type =="E") : "Incorrect type.";
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + getStatusIcon() + "] " + this.description;
    }

}
