import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *  Represents an event task.
 *  An event task has an event date and the start time and end time of event.
 */
public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.eventDate = date;
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Returns an Optional of the event date.
     * @return An Optional of event date.
     */
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

