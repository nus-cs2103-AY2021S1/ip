import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String STRING_FORMAT = "[E][%s] %s (at: %s)";

    protected LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(TaskType.EVENT, description);
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
    public String toString() {
        return String.format(Event.STRING_FORMAT, getStatusIcon(), description, 
                eventTime.format(Task.OUTPUT_DATE_TIME_FORMATTER));
    }
}
