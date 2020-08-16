public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone ? "[\u2713]" : "[\u2718]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTask() {
        return this.task;
    }
}
