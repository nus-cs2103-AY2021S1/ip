public class Task {
    private boolean hasCompleted;
    private String name;

    public Task(String name) {
        this.hasCompleted = false;
        this.name = name;
    }
    public void MarkAsDone() {
        this.hasCompleted = true;
    }

    public String toString() {
        return String.format("[%s] %s", this.hasCompleted ? "✓": "✗", this.name);
    }
}
