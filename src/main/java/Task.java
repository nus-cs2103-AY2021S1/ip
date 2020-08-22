public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.completed = "0";
    }

    protected String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    protected void markAsDone() {
        this.isDone = true;
        this.completed = "1";
    }

    protected String taskToText() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
