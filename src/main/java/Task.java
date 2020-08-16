public class Task {
    protected String description;
    protected boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    String getStatusIcon() {
        return (completed ? "\u2713" : "\u2718");
    }

    void markAsDone() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
