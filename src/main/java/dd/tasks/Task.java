package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String saveString() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}
