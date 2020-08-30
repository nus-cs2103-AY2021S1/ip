/**
 * Represents a task. The Task class is an abstract class because in the
 * context of the Duke application, it is necessary for the user to specify
 * a task as being a todo, deadline, or event.
 */
abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String object indicating whether the Task
     * is done.
     * @return String object indicating whether the Task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns an alternative String representation of the Task
     * @return String object representing the Task.
     */
    public abstract String getFormattedString();
}
