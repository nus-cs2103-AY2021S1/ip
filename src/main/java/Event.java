import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    private LocalDateTime eventTime;

    public Event(String description, boolean isDone, LocalDateTime eventTime) {
        super(TaskType.EVENT, description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public boolean hasDateTime() {
        return true;
    }
    
    @Override
    public LocalDateTime getDateTime() {
        return eventTime;
    }

    @Override
    public String toTaskData() {
        return String.format(Event.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description,
                eventTime.format(Viscount.TASK_DATA_DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, 
                eventTime.format(Task.OUTPUT_DATE_TIME_FORMATTER));
    }
}
