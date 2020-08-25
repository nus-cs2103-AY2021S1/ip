package duke.tasks;

public class Task {
    public boolean isDone;
    public String name;
    public char tick = '\u2713';
    public char cross = '\u2717';

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
        return String.format("[%c] %s", isDone ? tick : cross, name);
    }
}
