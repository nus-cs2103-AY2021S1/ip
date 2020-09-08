/**
 * Represents a task that the user wishes to save.
 */
public class Task {
    protected String description;
    protected boolean isComplete;

    /**
     * Constructor of the task object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isComplete = false;
    }

    protected Task(String description, boolean bool) {
        this.description = description;
        isComplete = bool;
    }

    public Task changeDesc(String newDesc) {
        return new Task(newDesc, this.isComplete);
    }

    /**
     * Marks a task as completed or done.
     * @return Completed task.
     */
    public Task markDone() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[T][\u2713] " + this.description;
        } else {
            return "[T][\u2718] " + this.description;
        }
    }
}
