/**
 * Encapsulates a to-do list type of task for the Duke program. A task is
 * inclusive of a textual description, a boolean flag to mark whether it has
 * been completed or not, and an index for where it appears in a list of tasks.
 */
public class Task {
    protected String description; // Describes the task
    protected boolean isDone; // Marks whether the task is completed or not
    protected int index; // The index for where the task appears in a list of
    // tasks

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return index + ".[" + getStatusIcon() + "] " + description;
    }
}
