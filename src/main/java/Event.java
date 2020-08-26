import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    @Override
    public String toString() {
        String symbol = this.done ? "✓" : "✘";
        return String.format("[E]%s (at: %s)", super.toString(),
                timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
