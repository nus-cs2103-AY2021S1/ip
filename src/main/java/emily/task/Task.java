package emily.task;

/**
 * Represents the basic structure of a sample task from the user input.
 * Contains a string of description detail.
 */
public class Task {
    protected String description;
    protected char type;
    protected boolean hasFinished;

    /**
     * Task with description, default is marked as not done
     * @param description contains a string of information about the task
     */
    public Task(String description) {
        this.description = description;
        this.hasFinished = false;
    }

    public void setHasFinished(boolean b) {
        this.hasFinished = b;
    }

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public String getDescription() {
        return this.description;
    }

    public void modifyDescription(String d) {
        this.description = d;
    }

    //return a String of a tick or cross indicating completion of task
    public String getStatusIcon() {
        return (hasFinished ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
