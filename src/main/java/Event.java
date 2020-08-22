import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time =time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDate() {
        return time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
