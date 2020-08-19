public class Task {
    private final String taskName;
    private boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void doTask() {
        isDone = true;
    }

    public void undoTask() {
        isDone = false;
    }
}
