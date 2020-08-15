package task;

public abstract class Task {
    protected final String task;
    protected boolean completed;

    protected static char TICK = 10003;
    protected static char CROSS = 10007;

    Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public abstract String toString();

    public String toStringSuffix() {
        if (this.completed) {
            return String.format("[%c] %s", TICK, this.task);
        } else {
            return String.format("[%c] %s", CROSS, this.task);
        }
    }

    public void completeTask() {
        this.completed = true;
    }
}
