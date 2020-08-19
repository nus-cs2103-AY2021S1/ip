

public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsDone() {
        completed = true;
    }

    @Override
    public String toString () {
        if (completed) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
