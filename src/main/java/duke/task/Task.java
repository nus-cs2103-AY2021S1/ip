package duke.task;

/**
 * Encapsulates information for a task -- a <code>Todo, Deadline</code> or <code>Event</code> and
 * contains operations for marking it as done, editing its description, and generating it as a String
 * for display or saving to data file.
 *
 * @author Hui Ling
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for new tasks created by user input when the app is running,
     * sets <code>isDone</code> as false by default.
     *
     * @param description  task description or name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Use of second constructor learnt from Lim Jia Rui Ryan's IP
    /**
     * Constructor for tasks loaded in from data file,
     * where it's known whether the task is done or not.
     *
     * @param description  task description or name
     * @param isDone       true if task is done, false if not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Changes <code>isDone</code> boolean to <code>true</code>.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void editDescription(String newDesc) {
        this.description = newDesc;
    }

    private String getStatusIcon() {
        return (isDone ? ":)" : ":("); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Represents the <code>Task</code> as a <code>String</code> in the specific format
     * for saving to data file, such that it can be read by <code>Storage</code>'s
     * <code>loadInputFromFile</code> method after the app is closed and run again.
     *
     * @return  a String representing the task in the format for saving to data file
     */
    public String saveToFile() {
        return isDone ? "1/" : "0/" + description;
    }
}
