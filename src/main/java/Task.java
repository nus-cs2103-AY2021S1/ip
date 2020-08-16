public class Task {
    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        isDone = false;
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}