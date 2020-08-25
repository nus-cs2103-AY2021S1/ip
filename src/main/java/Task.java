public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] "
                + this.description;
    }

    public Task fromString(String description) {
        return new Task(description);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "Done" : "Not Done");
    }
}
