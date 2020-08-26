import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String writeMessage() {
        String done = "";
        if (this.isDone) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("E | %s | %s", done, this.description, this.time);
    }

    @Override
    public String toString() {
        String str = " (at: ";
        str += time.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E][" + (this.isDone ? "✓" : "✗") + "] " + this.description + str + ")";
    }
}
