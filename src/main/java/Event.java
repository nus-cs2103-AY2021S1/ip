import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String time;
    protected LocalDate date;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        this.date = null;
    }

    public Event(String description, String time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }


    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[E]" + super.toString() + " (at: " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter
                    .ofPattern("MMM d yyyy")) + ")";
        }
    }
}
