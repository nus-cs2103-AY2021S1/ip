package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor for task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Get the description for this task
    public String getDescription() {
        return description;
    }

    // Check if this task is done or not
    public boolean isDone() {
        return isDone;
    }

    // Mark this task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Return the appropriate icon for this task
    public String getIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }
}
