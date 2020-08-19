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

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "Done" : "Not Done"); // for manual testing
    }
}
