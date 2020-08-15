public class Task {
    private boolean completed;
    private String taskName;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    private String getStatus() {
        return (this.completed ? "\u2713" : "\u2718");
    }

    public void markDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }

}
