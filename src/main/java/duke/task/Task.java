package duke.task;

import java.util.Collection;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected boolean getStatus() {
        return isDone;
    }

    protected String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    protected void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
