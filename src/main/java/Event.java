import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;

    public Event(String name, LocalDate time, boolean status) {
        super(name, status);
        this.time = time;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + this.time + ")";
    }

    @Override
    public LocalDate getTime() {
        return this.time;
    }

    @Override
    public String getType() {
        return "E";
    }
}
