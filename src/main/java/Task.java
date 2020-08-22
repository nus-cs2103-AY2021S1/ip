public abstract class Task {
    protected String description;
    protected boolean done = false;

    protected Task(String description) {
        this.description = description;
    }

    protected boolean isDone() {
        return this.done;
    }

    protected Task markAsDone() {
        this.done = true;
        return this;
    }

    protected String textFormat() {
        return (this.done ? "1, " : "0, ") + this.description;
    }

    @Override
    public String toString() {
        String status = this.done ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}
