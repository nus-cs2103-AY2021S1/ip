package duke.task;

import java.util.Optional;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() { return isDone; }

    public String isDoneToString() { return isDone ? "1" : "0"; }

    public abstract String getStringType();

    public abstract Optional<String> getDate();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public boolean contains(String keyWords) {
        return description.contains(keyWords);
    }
}
