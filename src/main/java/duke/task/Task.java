package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    protected final String description;
    protected final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    public String getData() {
        return (isDone ? "1" : "0") + "|" + description;
    }

    public abstract Task markAsDone();

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
