import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime datetime;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("d MMM yyyy hh.mma");

    public Event(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.parse(datetime, Event.inputFormatter);
    }

    private String datetimeString() {
        return "(at: " + this.datetime.format(Event.printFormatter) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.datetimeString();
    }
}
