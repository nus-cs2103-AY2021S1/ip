public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
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