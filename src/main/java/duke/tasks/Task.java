package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

}
