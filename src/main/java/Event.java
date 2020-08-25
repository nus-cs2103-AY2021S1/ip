import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String duration;

    public Event(String name, String details) {
        super(name);
        this.duration = details;
    }

    // Gets duration of the task
    public String getEventDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        //return this.duration.format(formatter);
        return this.duration;
    }

    @Override
    public String toString() {
        // By default print task name
        return "[E]" + super.toString() + " (at: " + getEventDetails() + ")";
    }
}
