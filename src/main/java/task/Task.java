package task;

public class Task {
    private final String task;
    private boolean completed;

    private static char TICK = 10003;
    private static char CROSS = 10007;

    Task(String task, boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public static Task createTask(String task) {
        return new Task(task, false);
    }

    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[%c] %s", TICK, this.task);
        } else {
            return String.format("[%c] %s", CROSS, this.task);
        }
    }
}
