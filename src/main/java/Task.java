import java.time.LocalDateTime;

public class Task {
    protected String details;
    protected boolean isCompleted;
    protected LocalDateTime dateTime;

    public Task(String details) {
        this.details = details;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "✓" : "✗") + "] " + details;
    }
}
