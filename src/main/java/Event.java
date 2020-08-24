import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    Event(String description, String at, boolean done) {
        super(description, done);
        this.at = LocalDate.parse(at);
    }
    @Override
    public String inputStyle() {
        return "event " + super.inputStyle() + " /at" +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
