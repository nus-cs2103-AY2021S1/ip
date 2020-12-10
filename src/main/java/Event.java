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

    /**
     * Create an event task
     * @param description of task
     * @param eventDate of task
     * @param startTime of task
     * @param endTime of task
     * @param notes of task
     */
    public Event(String description, LocalDate eventDate, LocalTime startTime,
                 LocalTime endTime, Optional<String> notes) {
        super(description, notes.map(note -> "|Note: " + note));
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
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
        return "E|" + super.toString() + "|"
                + eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "|"
                + startTime.format(DateTimeFormatter.ofPattern("hh.mma")) + " to "
                + endTime.format(DateTimeFormatter.ofPattern("hh.mma"))
                + notes.orElse("");
    }
}

