public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + this.description;
    }

    public String fileText() {
        return "| " + (isDone ? "1" : "0") + " | " + this.description;
    }
}
