import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event reminder.
 */
public class Event extends Task {
    /**
     * The time/date of the event.
     */
    protected String descriptionAfterAt;
    /**
     * The time/date of the event in terms of LocalDateTimeObject.
     */
    protected LocalDateTime localTime;

    /**
     * Constructs an Event object with the specified description and date and/or time.
     *
     * @param description        The description of this event.
     * @param descriptionAfterAt The date/time of this event.
     * @throws DateTimeParseException Throws if there is a wrong format of date/time passed.
     */
    public Event(String description, String descriptionAfterAt) throws DateTimeParseException {
        super(description);
        this.descriptionAfterAt = descriptionAfterAt;
        this.localTime = LocalDateTime.parse(descriptionAfterAt, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Returns the String representation of this event for saving.
     *
     * @return The String representation of this event for saving.
     */
    @Override
    public String writeSaveFormat() {
        return String.format("E | %d | %s | %s | %s", isDone
                ? 1 : 0, description, descriptionAfterAt, hasTag ? tagName : "");
    }

    /**
     * Returns the String representation of this event for the user.
     *
     * @return The String representation of this event for the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + localTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
