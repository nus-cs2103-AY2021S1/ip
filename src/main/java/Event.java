import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timePeriod;

    public Event(String name, String timePeriod) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    public Event(String name, String timePeriod, boolean done) {
        super(name, done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    @Override
    public String toFileFormat() {
        return "E" + " | " + super.toFileFormat() + " | " + this.timePeriod + "\n";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
