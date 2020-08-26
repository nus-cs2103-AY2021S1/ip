package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Deadline Task.
 */
public class Deadline extends Task {

    protected LocalDateTime localDateTime;

    /**
     * Instantiates Deadline.
     *
     * @param description The description of deadline.
     * @param localDateTime The local date and time for said deadline.
     */
    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    /**
     * Instantiates Deadline.
     *
     * @param description The description of deadline.
     * @param isDone Status of completion of deadline.
     * @param localDateTime The local date and time for said deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime localDateTime) {
        super(description, isDone);
        this.localDateTime = localDateTime;
    }

    /**
     * Formats the date and time.
     *
     * @return String containing the formatted date and time.
     */
    String formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyy, hh:mm a");
        return this.localDateTime.format(formatter);
    }

    /**
     * Overrides toString() method.
     *
     * @return String for Deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime() + ")";
    }
}
