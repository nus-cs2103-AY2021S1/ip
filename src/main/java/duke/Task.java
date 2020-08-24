package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }

    protected Task setDone() {
        this.isDone = true;
        return this;
    }

    protected String getTxtFormat() {
        return (this.isDone ? "1, " : "0, ") + this.description;
    }

    @Override
    public String toString() {
        String status = (this.isDone) ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}
