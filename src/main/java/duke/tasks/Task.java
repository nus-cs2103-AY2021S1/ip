package duke.tasks;

public class Task {

    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String mark = isDone ? "\u2713" : "\u2717";
        return String.format("[%s] %s", mark, task);
    }

    public String fileString() {
        return String.format("%s|%s", task, isDone ? "1" : "0");
    }
}
