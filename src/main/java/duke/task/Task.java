package duke.task;

public class Task {
    protected boolean isDone;
    protected String details;

    public Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    public Task(String details, boolean done) {
        this.details = details;
        this.isDone = done;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String store() {
        return details;
    }

    @Override
    public String toString() {
        String s = this.isDone ? "[✓] " : "[✗] ";
        return s + this.details;
    }
}
