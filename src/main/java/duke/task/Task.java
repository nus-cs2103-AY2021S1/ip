package duke.task;

public abstract class Task implements Serialisable {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public String serialise() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

}
