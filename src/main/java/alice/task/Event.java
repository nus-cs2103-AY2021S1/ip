package alice.task;

import alice.AliceException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    private static final DateTimeFormatter E_DATE_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu");
    private static final DateTimeFormatter E_DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu, ha");

    private final LocalDateTime on;
    private final boolean includesTime;

    /**
     * Creates a undone event happening at the specified time.
     *
     * @param description describes the event.
     * @param on the date and time of the event.
     */
    public Event(String description, LocalDateTime on) {
        super(description);
        this.on = on;
        this.includesTime = !on.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    /**
     * Creates an event happening at the specified time.
     *
     * @param isDone the completion status of the event, true if completed; false otherwise.
     * @param description describes the event.
     * @param on the date and time of the event.
     */
    public Event(boolean isDone, String description, LocalDateTime on) {
        super(isDone, description);
        this.on = on;
        this.includesTime = !on.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    /**
     * Gets the string representation of the event date and time.
     * If time is not specified by user, the time is omitted from the string representation.
     *
     * @return the appropriate string representation of the event datetime.
     */
    public String getEventDateTime() {
        if (includesTime) {
            return on.format(E_DATETIME_FORMAT);
        } else {
            return on.format(E_DATE_FORMAT);
        }
    }

    /**
     * Decode an encoded string representation of the event.
     *
     * @param saved the string representation of the encoded event.
     * @return the <code>Event</code> described in the string representation.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static Event decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Event(isDone, inputs[1], LocalDateTime.parse(inputs[2]));
        } else {
            throw new AliceException("Corrupted event data");
        }
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + on.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + getEventDateTime() + ")";
    }
}
