package duke;

/**
 * Represents a work that needs to be done.
 * Contains a name of that work, represented by a String
 * Contains the done status of the work. represented by a boolean.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isDone = false;
    }

    /**
     * Visualises the done status of a task with an icon
     *
     * @return String version of check and cross, whichever needed
     */
    public String getStatusIcon() {
        return isDone ?  "\u2713" : "\u2718";
    }

    public String getDate() {
        return "";
    }

    /**
     * Changes the task status to finished/done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }
}
