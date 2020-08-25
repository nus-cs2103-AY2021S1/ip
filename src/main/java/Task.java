public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getType() {
        return "?";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String toFileString() {
        int stat;
        if (this.isDone) {
            stat = 1;
        } else {
            stat = 0;
        }
        return String.format("%s | %d | %s", this.getType(), stat, this.description);
    }
}
