package tickbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import tickbot.util.DateTimeFormatterFactory;

/**
 * The class to represent an abstract task.
 */
public abstract class Task {
    private final String content;
    private final LocalDateTime time;
    private boolean isCompleted;
    private List<String> tags;

    Task(boolean isCompleted, String content, LocalDateTime time, List<String> tags) {
        assert content != null; // content cannot be null
        assert tags != null;
        this.content = content;
        this.isCompleted = isCompleted;
        this.time = time;
        this.tags = tags;
    }

    /**
     * Gets the string to represent the type of the task (e.g. T, D, E, ...)
     * @return the type string.
     */
    public abstract String getTaskType();
    /**
     * Gets the word used before the time of the task (e.g. by, at, ...)
     * @return The word used, {@code null} if none.
     */
    public abstract String getTimeMarker();

    /**
     * Gets the content of the task.
     * @return the content string.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the status whether the task is completed.
     * @return the boolean status.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Gets the date of the task.
     * @return A {@LocalDateTime} object if the task contains a time,
     *         {@code null} if none.
     */
    public LocalDateTime getTime() {
        return time;
    }

    public void setCompleted(boolean status) {
        isCompleted = status;
    }

    private String getCompleteMark() {
        return isCompleted() ? "✔︎" : "✘";
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        String timeMarker = getTimeMarker();
        String result = "";
        if (timeMarker != null) {
            DateTimeFormatter formatter = DateTimeFormatterFactory.getOutputFormatter();
            String timeString = formatter.format(getTime());
            result += String.format("[%s][%s] %s (%s: %s)", getTaskType(),
                    getCompleteMark(), getContent(), timeMarker, timeString);
        } else {
            result += String.format("[%s][%s] %s", getTaskType(),
                    getCompleteMark(), getContent());
        }
        String tagString = tags.stream()
                .map(tag -> "#" + tag)
                .collect(Collectors.joining(" "));
        return tagString.isEmpty() ? result : result + " " + tagString;
    }
}
