import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime at;

    public Events(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        String dateTime = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
