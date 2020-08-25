package duke.tasks;

public class Task {
    public boolean isDone;
    public String name;

    public Task(String name) {
        isDone = false;
        this.name = name;
    }

    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", isDone ? "DONE" : "NOT DONE", name);
    }
}
