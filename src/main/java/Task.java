public class Task {
    String task;
    boolean isCompleted;

    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    void completed() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[\u2713] " + task;
        } else {
            return "[\u2718] " + task;
        }
    }
}
