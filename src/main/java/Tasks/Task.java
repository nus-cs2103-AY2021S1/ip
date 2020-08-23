package Tasks;

public class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getIsDone() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getIsDone() + this.getTaskName();
    }
}
