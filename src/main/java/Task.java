import java.io.Serializable;

abstract class Task implements Serializable {

    // Attributes
    protected final String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) throws EmptyBodyException {
        if (description.isEmpty()) {
            throw new EmptyBodyException("description", this.getClass().toString());
        }

        this.description = description;
        this.isDone = false;
    }

    // Methods
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    // String Representation
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
