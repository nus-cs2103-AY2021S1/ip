package duke;
public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() { return ""; }

    public String getDescription() {
        return this.description;
    }

    public void done() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //...
}