public class Task {
    private Boolean done;
    private String taskName;
    public Task(String taskName) {
        this.done = false;
        this.taskName = taskName;
    }
    public void markAsDone() {
        this.done = true;
    }

    public String getTaskStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
