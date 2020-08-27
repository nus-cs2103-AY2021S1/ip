package main.java.emily.task;

public class Task {
    protected String description;
    protected boolean isFinished;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    public void setFinished(boolean b) {
        this.isFinished = b;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isFinished ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
