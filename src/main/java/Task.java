import java.util.stream.Stream;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to print status icon
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Method to set task to be completed
    public void markAsDone() {
        this.isDone = true;
    }

    // Method to return status of task
    public boolean getStatus() {
        return this.isDone;
    }

    // Method to print a task
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
