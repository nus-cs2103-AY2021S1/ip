public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon() + "] "
                + this.description;
    }

    public String display() {
        return "";
    }
}