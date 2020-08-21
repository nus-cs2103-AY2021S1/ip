import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    public Event(String name, boolean done, LocalDate at) {
        super(name, done);
        this.at = at;
    }

    @Override
    public Event setDone(boolean b) {
        return new Event(this.getName(), true, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
