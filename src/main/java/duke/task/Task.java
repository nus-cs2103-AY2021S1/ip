package duke.task;

/**
 * Represents a task with a description and a boolean.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void makeDone() {
        isDone = true;
    }

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    public String toData() {
        String done = isDone ? "1" : "0";
        return done + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
