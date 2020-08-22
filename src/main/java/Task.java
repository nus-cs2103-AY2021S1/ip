public abstract class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.done;
    }

    public Task markAsDone() {
        this.done = true;
        return this;
    }

    public String textFormat() {
        return (this.done ? "1, " : "0, ") + this.description;
    }

    @Override
    public String toString() {
        String status = this.done ? "[✓]" : "[✗]";
        return status + " " + description;
    }

}
