public class Task {
    boolean isDone;
    String task;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public void makeDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n", getStatusIcon(), this.task);
    }
}
