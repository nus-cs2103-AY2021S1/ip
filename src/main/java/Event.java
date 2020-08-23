import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task{
    protected LocalDate eventDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.eventDate = date;
        this.startTime = start;
        this.endTime = end;
    }

    public Optional<LocalDate> getDate() {
        return Optional.of(eventDate);
    }

    @Override
    public String toString() {
        return "E|" + super.toString() + "|" +
                eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "|" +
                startTime.format(DateTimeFormatter.ofPattern("hh.mma")) + " to " +
                endTime.format(DateTimeFormatter.ofPattern("hh.mma"));
    }
}

