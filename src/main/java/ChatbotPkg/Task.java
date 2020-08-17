package ChatbotPkg;

public class Task {
    protected final String description;
    protected final String type;
    protected final boolean isDone;

    protected Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    protected Task(String description, String type, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markDone() {
        return new Task(this.description, this.type, true);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "]" +
                " " + this.description;
    }
}
