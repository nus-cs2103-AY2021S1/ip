import java.time.LocalDateTime;

/**
 * Represents an event with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs an event with a deadline.
     * @param description the description of the event.
     * @param by the deadline of the event.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if(by.getMinute() >= 10) {
            return "[D]" + super.toString() + " (by: " + by.getMonth().toString().substring(0, 3) + " " +
                    by.getDayOfMonth() + " " + by.getYear() + " " +
                    String.format("%d:%d)", by.getHour(), by.getMinute()) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.getMonth().toString().substring(0, 3) + " " +
                    by.getDayOfMonth() + " " + by.getYear() + " " + by.getHour() + ":0" + by.getMinute() + ")";
        }

    }
}
