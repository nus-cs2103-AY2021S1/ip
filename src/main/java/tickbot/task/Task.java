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

    protected String getCompleteMark() {
        return isCompleted() ? "✔︎" : "✘";
    }
}