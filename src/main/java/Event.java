import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task{
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public LocalDate getEventDate() {
        return this.at;
    }

    /**
     * Returns the format for permanent storage of an event task in file.
     * @return string format for storing.
     */
    @Override
    public String getStoringFormat() {
        return "E " + super.getStoringFormat() + " ~ " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
