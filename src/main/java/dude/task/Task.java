package dude.task;


/**
 * The class that contains the key functionality of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another constructor for the Task class.
     *
     * @param description description of the task.
     * @param isDone boolean value to denote if a task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return String description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a string representation of the task to be written into the data file.
     *
     * @return String formatted description.
     */
    public String toSave() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Overrides equal to compare 2 Task objects.
     *
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return task.getDescription().trim().equals(description.trim());
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return String formatted description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
