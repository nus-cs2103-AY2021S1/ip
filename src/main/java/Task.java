public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
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
        return isDone ? "✓" : "✘";
    }

    public String encode() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
