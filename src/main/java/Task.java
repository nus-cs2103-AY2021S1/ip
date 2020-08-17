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
}
