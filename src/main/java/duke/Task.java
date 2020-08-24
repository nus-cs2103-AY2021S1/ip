package duke;

/**
 * Task class will handle user's tasks.
 */
public class Task {
    protected String description; // Description of the task
    protected boolean isDone; // Status of the completion of the task

    /**
     * Initialise Task using description only.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialise Task using description and isDone.
     * Used when knowledge about isDone is needed, eg. loading existing list from hard disk.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns symbol representation about the completion status of the task.
     * @return tick (completed) or X (not completed) symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns number representation about the completion status of the task.
     * @return 1 (completed) or 0 (not completed)
     */
    public String getStatusIndex() { return (isDone ? "1" : "0" );}

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Return string format of task.
     * @return String description of to-do task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return string format of to-do task that will be written on a text file.
     * @return String description of to-do task
     */
    public String writeToFile() {
        return " | " + getStatusIndex() + " | " + description;
    }
}