package task;

public class Task {
    private int hasCompleted;
    private String name;

    public Task(String name) {
        this.hasCompleted = 0;
        this.name = name;
    }

    public Task(String name, int hasCompleted) {
        this.hasCompleted = hasCompleted;
        this.name = name;
    }
    public void MarkAsDone() {
        this.hasCompleted = 1;
    }

    public int getHasCompleted() {
        return hasCompleted;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s] %s", this.hasCompleted == 1 ? "✓": "✗", this.name);
    }
}
