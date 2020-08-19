public class Task {
    protected final String description;
    protected final boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        Task task = new Task(this.description, true);
        return task;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", box, this.description);
    }
}
