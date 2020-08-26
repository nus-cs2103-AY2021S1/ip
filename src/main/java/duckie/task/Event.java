package duckie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDate() {
        return dtf.format(this.dateTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dtf.format(this.dateTime) + ")";
    }
}
