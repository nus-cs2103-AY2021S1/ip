public class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");

    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
