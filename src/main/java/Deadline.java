import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Defines a Deadline type task.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Deadline extends Task {

    private LocalDate localDate;
    private LocalTime localTime;

    Deadline(String description, String localDate, String localTime) {
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    /**
     * Prints the description of the Deadline, with date/time if available.
     *
     * @return String describing the Deadline task.
     */
    @Override
    public String toString() {
        String toReturn = "[D]" + super.toString();
        if (this.hasDate()) {
            if (this.hasTime()) {
                toReturn += " (by: " + this.localDate.format((
                        DateTimeFormatter.ofPattern("MMM dd yyyy"))) + " " + this.localTime + ")";
            } else {
                toReturn += " (by: " + this.localDate.format((
                        DateTimeFormatter.ofPattern("MMM dd yyyy"))) + ")";
            }
        }
        return toReturn;
    }
}
