import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, LocalDate date, LocalTime startTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
    }

    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                (startTime != null ? startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "") +
                (endTime != null ? "-" + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "") + ")";
    }
}
