// partial solution template copied from Extension: A-Classes

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        assert description.trim() != "" : "The description of a task cannot be empty!";
        this.description = description.trim();
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
