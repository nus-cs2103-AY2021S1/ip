import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(String taskName, LocalDateTime dateTime)  {
        super(taskName);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
    }
}
