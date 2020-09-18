import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Event object.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    /**
     * Saves whether the task is done or not into storage.
     * @return a string representation of the task.
     */
    @Override
    public String saveTask() {
        return "E" + super.saveTask() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}