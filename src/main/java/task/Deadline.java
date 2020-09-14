package task;

/**
 * A subclass of Task.
 * Contains a task description and a time.
 */
public class Deadline extends Task {
    private String description;
    private boolean isDone;
    private String by;
    /**
     * Constructor of Deadline object.
     * @param description description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        this.description = description;
        this.isDone = false;
        this.by = by;
    }

    /**
     * Overloaded constructor of Deadline object.
     * @param description description of the task.
     * @param isDone the status of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
    }

    /**
     * Returns the description of the deadline task.
     * @return the description of the deadline task.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns the icon for corresponding status of task.
     * @return sign of tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    /**
     * Marks the deadline task as done.
     * @return new Deadline object with true for isDone.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.by);
    }

    /**
     * Returns a string of the task object to be saved in data file.
     * @return string in the format of data in data file.
     */
    @Override
    public String toStringOfDatabase() {
        String number = isDone ? "1" : "0";
        return "D | " + number + " | " + this.description + " | " + this.by;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + by + ")";
    }
}
