package main.java;

/**
 * Tasks that Duke can do for the users
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Visualizes the isDone() to a String
     * @return the icon that represents whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * prints the task in a easy-read manner
     */
    public void printDescription() {
        System.out.println("[" + getStatusIcon() + "] " + description);
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
