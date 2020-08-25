/**
 * Represents a task. The Task class is an abstract class because in the context
 * of the Duke application, it is necessary for the user to specify a task as being
 * a todo, deadline, or event.
 */
abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String getFormattedString();
}
