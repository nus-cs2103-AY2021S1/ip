import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String task, LocalDateTime at) {
        super(task);
        this.at = at;
    }

    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("E | %s | %s", done, this.task, this.at);
    }

    @Override
    public String toString() {
        String str = " (at: ";
        str += at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
