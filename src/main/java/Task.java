public class Task {
    protected final String description;
    protected final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isitDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    public Task markDone() {
        return new Task(getDescription(), true);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
