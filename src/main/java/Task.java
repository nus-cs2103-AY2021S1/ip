public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + (this.isDone ? "\u2713" : "\u2718") + "] " + this.name;
    }
}
