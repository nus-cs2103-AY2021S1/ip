package task;
/**
 * A subclass of Task.
 * Contains a task description and a time.
 */
public class Todo extends Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of Todo object.
     * @param description description of the task.
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor of Todo object.
     * @param description description of the task.
     * @param isDone the status of the task.
     */
    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Returns the description of the todo task.
     * @return the description of the todo task.
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
     * Marks the Todo task as done.
     * @return new Todo object with true for isDone.
     */
    @Override
    public Todo markAsDone() {
        return new Todo(this.description, true);
    }

    /**
     * Turns task object into a string to be saved in data file.
     * @return string in the format of data in data file.
     */
    @Override
    public String toStringOfDatabase() {
        String number = isDone ? "1" : "0";
        return "T | " + number + " | " + this.description;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
