import java.io.Serializable;

abstract class Task implements Serializable {

    // Attributes
    protected final String description;
    protected boolean isDone;

    // Constructor

    /**
     * Creates a generic task.
     * @param description description of the task.
     * @throws EmptyBodyException If description is empty.
     */
    public Task(String description) throws EmptyBodyException {
        if (description.isEmpty()) {
            throw new EmptyBodyException("description", this.getClass().toString());
        }

        this.description = description;
        this.isDone = false;
    }

    // Methods
    private String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    // String Representation
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
