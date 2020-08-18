public class Task {
    private String taskName;
    private boolean completed;

    Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getName() {
        return taskName;
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
