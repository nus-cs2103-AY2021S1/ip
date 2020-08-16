public class Task {
    private static int taskCount = 0;
    private int taskId;
    private boolean completed;
    private String description;

    private static final String TICK = "\u2713", CROSS = "\u2718";

    public Task(String description) {
        this.taskId = ++ taskCount;
        this.description = description;
        this.completed = false;
    }

    @Override
    public String toString() {
        return this.taskId + ". " + this.description;
    }
}
