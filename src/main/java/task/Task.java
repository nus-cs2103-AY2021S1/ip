package task;

public class Task {
    private int hasCompleted;
    private String name;

    /**
     * Default Constructor for Task
     * @param name Name of Task
     */
    public Task(String name) {
        this.hasCompleted = 0;
        this.name = name;
    }

    /**
     * Alternative Constructor for Task
     * @param name Name of Task
     * @param hasCompleted boolean to determine whether task is completed
     */
    public Task(String name, int hasCompleted) {
        this.hasCompleted = hasCompleted;
        this.name = name;
    }

    public void markAsDone() {
        this.hasCompleted = 1;
    }

    public int getHasCompleted() {
        return hasCompleted;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s] %s", this.hasCompleted == 1 ? "✓" : "✗", this.name);
    }
}
