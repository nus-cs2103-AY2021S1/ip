import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    Event(String taskName, boolean isCompleted, LocalDateTime date) {
        super(taskName, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                date.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + ")";
    }
}
