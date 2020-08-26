package duke;

import java.io.IOException;
import java.io.PrintWriter;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    abstract String getTaskDetailsForSave();

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
