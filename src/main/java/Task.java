public class Task {
    private String taskName;
    private boolean taskCompleted;

    public Task(String isCompleted, String taskName) {
        this.taskName = taskName;
        if (isCompleted.equals("0")) {
            this.taskCompleted = false;
        } else {
            this.taskCompleted = true;
        }
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
