package main.java.emily.task;

/**
 * Represents the basic structure of a sample task from the user input.
 * Contains a string of description detail.
 */
public class Task {
    protected final String description;
    protected char type;
    protected boolean hasFinished;

    public Task(String description) {
        this.description = description;
        this.hasFinished = false;
    }

    public void setHasFinished(boolean b) {
        this.hasFinished = b;
    }

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (hasFinished ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
