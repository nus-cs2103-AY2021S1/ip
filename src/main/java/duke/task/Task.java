package duke.task;

public class Task {
    protected boolean done;
    protected String details;

    public Task(String details) {
        this.done = false;
        this.details = details;
    }

    public Task(String details, boolean done) {
        this.details = details;
        this.done = done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String store() {
        return details;
    }

    @Override
    public String toString() {
        String s = this.done ? "[✓] " : "[✗] ";
        return s + this.details;
    }
}
