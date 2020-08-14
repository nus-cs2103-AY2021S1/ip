public class Task {

    private boolean completed;
    private String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public Task markCompleted() {
        return new Task(description, true);
    }

    @Override
    public String toString() {
        String completionStatus = completed ? "Done!" : "X";
        return String.format("[%s] %s", completionStatus, description);
    }
}
