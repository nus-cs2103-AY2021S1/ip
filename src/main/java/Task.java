public class Task {
    private String taskName;
    private boolean taskCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskCompleted = false;
    }

    public String completeTask() {
        this.taskCompleted = true;
        return this.toString();
    }

    @Override
    public String toString() {
        if (taskCompleted) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
