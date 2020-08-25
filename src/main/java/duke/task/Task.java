package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public String getSaveFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }

}
