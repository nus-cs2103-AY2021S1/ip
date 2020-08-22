import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime time;
    private final DateTimeFormatter formatter;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
        formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(formatter));
    }
}
