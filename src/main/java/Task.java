/**
 * The Task class represent an abstract class of Tasks which can be used to represent
 * different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object with the given description. A constructor in an abstract
     * class cannot be instantiated.
     * @param description
     * @throws PandaBotEmptyTaskDescriptionException
     */
    public Task(String description) throws PandaBotEmptyTaskDescriptionException {
        this.description = description.strip(); // removes starting and ending white spaces
        if (this.description.length() == 0) {
            throw new PandaBotEmptyTaskDescriptionException(this.getClass().getSimpleName());
        }
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
    
    public String saveAsText() {
        return String.format("%d | %s", (isDone ? 1 : 0), description);
    }

    public void markTaskDone() {
        isDone = true;
    }
}
