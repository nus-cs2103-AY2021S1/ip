package duke.tasks;

/** Represents a task. */
public class Task {

    /** The description of this task. */
    protected String description;

    /** Represents whether the task is done or not. */
    protected boolean isDone;

    /** Constructs the task with the specified description. isDone is by default false.
     *
     * @param description The description of this task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** Marks this taks as done. */
    public void markAsDone() {
        isDone = true;
    }

    /** Returns the String representation of this task in the format to be saved in the file.
     *
     * @return The String representation of this task in the appropriate format.
     */
    public String toFileString() {
        return String.format("? | %d | %s", isDone ? 1 : 0, description);
    }

    /** Returns the String representation of this task to be displayed to the user.
     *
     * @return The String representation of this task to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
