package main.java.emily.task;

/**
 * Represents the basic structure of a sample task from the user input.
 * A task will have a string of description detail.
 */
public class Task {
    protected String description;
    protected char type;
    protected boolean isFinished;

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

    public char getType() { return this.type; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
