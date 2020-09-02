package task;

public class Task {
    private int hasCompletedInt;
    private String name;

    /**
     * Default Constructor for Task
     *
     * @param name Name of Task
     */
    public Task(String name) {
        this.hasCompletedInt = 0;
        this.name = name;
    }

    /**
     * Alternative Constructor for Task
     *
     * @param name         Name of Task
     * @param hasCompletedInt boolean to determine whether task is completed
     */
    public Task(String name, int hasCompletedInt) {
        this.hasCompletedInt = hasCompletedInt;
        this.name = name;
    }

    public void markAsDone() {
        this.hasCompletedInt = 1;
    }

    public int gethasCompletedInt() {
        return hasCompletedInt;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s] %s", this.hasCompletedInt == 1 ? "✓" : "✗", this.name);
    }
}
