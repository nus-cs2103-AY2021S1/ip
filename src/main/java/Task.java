public class Task {
    private final String task;
    private boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void completedTask() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + (this.isDone ? "\u2713" : "\u2717") + "] " + this.task;
    }
}
