public class Task {
    private boolean isDone;
    private String taskName;

    Task(String name) {
        this.taskName = name;
    }

    // returns false if task is already done
    public boolean markDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    public boolean getDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        String mark = isDone ? "✓" : "✗";
        return "[" + mark + "] " + taskName;
    }
}
