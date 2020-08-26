public class Task {
    protected String task;
    protected boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
    }

    public String getStatusToString() {
        return done ? "✓" : "✘";
    }

    @Override
    public String toString() {
        return "[" + getStatusToString() + "] " + task;
    }
}
