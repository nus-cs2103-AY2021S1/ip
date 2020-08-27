public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ?  "\u2713" : "\u2718";
    }

    public String getDate() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }
}
