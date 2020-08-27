package duke.task;

import java.util.Date;

public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected TaskType type;

    protected Task(String name, boolean isDone, TaskType type) {
        this.name = name;
        this.type = type;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public TaskType getType() {
        return this.type;
    }

    public abstract Date getDate();

    public void markAsDone() {
        this.isDone = true;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
