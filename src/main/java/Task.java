public class Task {
    String task;
    boolean isCompleted;

    public Task(String task) {
        this.task = task;
        isCompleted = false;
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
