import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    Event(String description, LocalDate at) {
        this(description, false, at);
    }

    private Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Return updated task of subtype: Event
     *
     * @param isDone New status for the task
     * @return new Event with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return new Event(super.description, isDone, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}