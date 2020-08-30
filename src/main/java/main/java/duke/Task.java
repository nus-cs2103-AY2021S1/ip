package main.java.duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of the Task class, the parent class for Todo, Deadline and Event classes
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol indicating if the task has been completed
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks this instance of Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this instance of Task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task object.
     * @return a string representation of the Task object.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
