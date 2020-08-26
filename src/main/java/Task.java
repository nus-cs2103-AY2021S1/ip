public class Task {
    protected String task;
    protected Boolean completed;

    Task(String task) {
        this.task = task;
        this.completed = false;
    }

    Task(String task, Boolean completed) {
        this.task = task;
        this.completed = completed;
    }

    public void updateStatus(Boolean completed) {
        this.completed = completed;
    }

    public String getDate() {
        return "";
    }

    public String getType() {
        return "";
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        String completedMarker = this.completed ? "✓" : "✗";
        return String.format("[%s] %s", completedMarker, this.task);
    }
}
