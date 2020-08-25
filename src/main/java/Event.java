package duke;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String desc, String at) {
        super(desc);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDisk() {
        return String.format("event\n%s\n%d\n%s", desc, (done == true ? 1 : 0), at);
    }
}