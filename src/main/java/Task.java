public class Task {
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        char checkMark = this.isDone ? '✓' : '✗';
        return "["+checkMark+"] "+this.description;
    }
}
