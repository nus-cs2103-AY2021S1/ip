public class Task {
    private static int taskCount = 0;
    private final int taskId;
    private boolean completed;
    private final String description;

    private static final String TICK = "\u2713", CROSS = "\u2718";

    public Task(String description) {
        this.taskId = ++taskCount;
        this.description = description;
        this.completed = false;
    }

    protected Task complete() {
        this.completed = true;
        return this;
    }

    protected boolean isComplete() {
        return this.completed;
    }
    public int getID() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? TICK : CROSS) + "] "
                + this.description;
    }
}
