package main.java.moco.task;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Changes task completion symbol from boolean value
     *
     * @return String with symbol formatting
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //returns tick or X symbol accordingly
    }

    public void markAsDone() {
        this.isDone = true;
    }

    abstract public String saveText();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
