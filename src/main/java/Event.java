import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    Event(String s, String time) {
        super(s);
        this.time = LocalDate.parse(time);
    }

    Event(String name, boolean completed, String time) {
        super(name, completed);
        this.time = LocalDate.parse(time);
    }

    private Event(String name, boolean completed, LocalDate time) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String getTime() {
        return time.toString();
    }

    @Override
    public Event completeTask() {
        return new Event(this.name, true, this.time);
    }

    @Override
    public String toString() {
        return "[event]" + super.toString()
                + " (at: " + this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
