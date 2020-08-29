package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "✓" : "✗");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String save(int isFinished) {
        return isFinished + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
