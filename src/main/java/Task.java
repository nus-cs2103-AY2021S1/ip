import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    protected TaskType taskType;
    protected String description;
    protected boolean isDone;

    public Task(TaskType taskType, String description, boolean isDone) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
        //return tick or X symbols
    }

    public abstract String toTaskData();

    public TaskType getTaskType() {
        return taskType;
    }

    public abstract boolean hasDateTime();
    public abstract LocalDateTime getDateTime();
}