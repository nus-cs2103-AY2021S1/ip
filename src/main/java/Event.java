import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Defines a Event type task.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Event extends Task {

    protected LocalDate localDate;
    protected LocalTime localTime;

    Event(String description, String localDate, String localTime) {
        super(description);
        this.localDate = localDate != "" ? LocalDate.parse(localDate) : null;
        this.localTime = localTime != "" ? LocalTime.parse(localTime) : null;
    }

    public boolean hasDate() {
        return this.localDate != null;
    }

    public boolean hasTime() {
        return this.localTime != null;
    }

    /**
     * Prints the description of the Event, with date/time if available.
     *
     * @return String describing the Event task.
     */
    @Override
    public String toString() {
        String toReturn = "[E]" + super.toString();
        if (this.hasDate()) {
            if (this.hasTime()) {
                toReturn += " (at: " + this.localDate.format((
                        DateTimeFormatter.ofPattern("MMM dd yyyy"))) + " " + this.localTime + ")";
            } else {
                toReturn += " (at: " + this.localDate.format((
                        DateTimeFormatter.ofPattern("MMM dd yyyy"))) + ")";
            }
        }
        return toReturn;
    }

}
