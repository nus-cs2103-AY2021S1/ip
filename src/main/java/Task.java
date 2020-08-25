/**
 * Represents a task entered by user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether the task is done.
     * @return Unicode character.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Checks whether the task is done.
     * @return Integer that represents done state.
     */
    public int doneState() {
        return (isDone ? 1 : 0);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
    
    public String saveString() {
        return " , " + doneState() + " , " + this.description;
    }
}

