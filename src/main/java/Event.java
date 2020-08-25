import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Event extends Task {

    private final LocalDate date;
    private final LocalTime time;

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

    @Override
    public String[] attributeList() {
        return new String[] { "E", getName(), date.toString(), time.toString(), String.valueOf(isDone()) };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(date, event.date) &&
                Objects.equals(time, event.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, time);
    }
}
