package task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    @Override
    public String toString() {
        return getStatus() + description;
    }
}
