package task;

public abstract class Task {
    protected final String task;
    protected boolean completed;

    protected static String TICK = "Y";
    protected static String CROSS = "N";

    Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public abstract String toString();

    public String toStringSuffix() {
        if (this.completed) {
            return String.format("[%s] %s", TICK, this.task);
        } else {
            return String.format("[%s] %s", CROSS, this.task);
        }
    }

    public void completeTask() {
        this.completed = true;
    }
}
