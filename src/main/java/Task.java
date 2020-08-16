public abstract class Task {

    private String taskName;
    private boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void competeTask() {
        isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
