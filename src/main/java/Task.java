public class Task {
    private Boolean isDone;
    private String taskName;
    private TaskType type;
    public Task(String taskName, TaskType type) {
        this.isDone = false;
        this.taskName = taskName;
        this.type = type;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getType() {
        return this.type.toString();
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
