package duke.task;

public class Task {
    private boolean isDone;
    private String description;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.isDone ? "[✓] " + this.description : "[✗] " + this.description;
    }
}
