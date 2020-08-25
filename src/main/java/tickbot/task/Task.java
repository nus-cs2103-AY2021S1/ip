package tickbot.task;

import java.time.LocalDate;

/**
 * The class to represent an abstract task.
 */
public abstract class Task {
    private final String content;
    private final LocalDate time;
    private boolean completed;

    /**
     * The string to represent the type of the task (e.g. T, D, E, ...)
     * @return the type string.
     */
    public abstract String getTaskType();
    /**
     * The word used before the time of the task (e.g. by, at, ...)
     * @return the word used, {@code null} if none.
     */
    public abstract String getTimeMarker();

    Task(boolean completed, String content, LocalDate time) {
        this.content = content;
        this.completed = completed;
        this.time = time;
    }

    /**
     * Get the content of the task.
     * @return the content string.
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the status whether the task is completed.
     * @return the boolean status.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Get the date of the task.
     * @return a {@LocalDate} object if the task contains a time, {@code null} if none.
     */
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