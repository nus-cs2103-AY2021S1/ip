package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}