package tasklist;

/**
 * Encapsulates a Task object in the Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon, if status is done a tick symbol is returned, else a cross symbol is returned.
     * @return A string object of the status symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     * @return The output string.
     */
    public String markDone() {
        if (this.isDone == false) {
            this.isDone = true;
            return "Nice! I've marked this task as done: " + "\n" + this.toString();
        } else {
            return "Task is already done.";
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
