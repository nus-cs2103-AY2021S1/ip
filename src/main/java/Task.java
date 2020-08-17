public class Task {
    private Boolean done;
    private String taskName;
    private TaskType type;
    public Task(String taskName, TaskType type) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
    }
    public void markAsDone() {
        this.done = true;
    }

    public String getTaskStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public String getType() {
        return this.type.toString();
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
