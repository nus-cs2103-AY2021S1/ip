public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatus() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.done = true;
    }
}
