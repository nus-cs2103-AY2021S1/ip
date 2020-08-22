package duke.task;

public class Task {
    public boolean done;
    public String description;

    Task(String description) {
        this.description = description;
        this.done = false;
    }

    Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public void done() {
        this.done = true;
    }

    @Override
    public String toString() {
        return this.done ? "[✓] " + this.description : "[✗] " + this.description;
    }
}
