public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        } else {
            return false;
        }
    }

    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
