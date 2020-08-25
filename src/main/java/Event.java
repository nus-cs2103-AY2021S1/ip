import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime taskDateTime;

    public Event(String desc, LocalDateTime taskDateTime) {
        super(desc);
        this.taskDateTime = taskDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.taskDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
