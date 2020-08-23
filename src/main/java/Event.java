import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task
 */

class Event extends Task {
    protected LocalDateTime datetime;

    public Event(String name, LocalDateTime datetime, boolean done) {
        super(name, done);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String writeString() {
        return "E # " + super.writeString() + " # " + datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
