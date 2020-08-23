import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDate date;
    private final LocalTime time;

    public Event(String name, LocalDate date) throws BlankTaskException {
        super(name);
        this.date = date;
        this.time = null;
    }

    public Event(String name, LocalDate date, LocalTime time) throws BlankTaskException {
        super(name);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("EEE, d MMM yy"))
                + (time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("h.mm a"))) + ")";
    }
}
