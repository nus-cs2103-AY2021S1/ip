public abstract class Task {
    public String taskName;
    public boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public void completed() {
        isDone = true;
    }

    public abstract String getInfo();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713" : "\u2718", taskName);
    }

}
