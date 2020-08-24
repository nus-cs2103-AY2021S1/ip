import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    private Event (String task, LocalDateTime date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }

    public Event(String task, LocalDateTime date) {
        super(task);
        this.date = date;
    }

    @Override
    public Event markDone() {
        return new Event(task, date, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)",
                date.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
    }
}