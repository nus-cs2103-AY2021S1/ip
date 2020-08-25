package tickbot.task;

import java.time.LocalDate;

/**
 * The class to represent a task.
 */
public abstract class Task {
    private final String content;
    private final LocalDate time;
    private boolean completed;

    public abstract String getTaskType();
    public abstract String getTimeMarker();

    Task(boolean completed, String content, LocalDate time) {
        this.content = content;
        this.completed = completed;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setCompleted(boolean status) {
        completed = status;
    }

    private String getCompleteMark() {
        return isCompleted() ? "✔︎" : "✘";
    }

    @Override
    public String toString() {
        String timeMarker = getTimeMarker();
        if (timeMarker != null) {
            return String.format("[%s][%s] %s (%s: %s)", getTaskType(), 
                    getCompleteMark(), getContent(), timeMarker, getTime());
        } else {
            return String.format("[%s][%s] %s", getTaskType(),
                    getCompleteMark(), getContent());
        }
    }
}