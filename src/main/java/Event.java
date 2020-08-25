import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event that the user has.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate atDate;
    protected LocalDateTime atDateTime;

    /**
     * Initializes an event with its description, and when it will be.
     * @param description The description of the event.
     * @param at When the event will be at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.setAtDateAndTime();
    }

    /**
     * Initializes an event with its description, whether it is done, and when it will be.
     * @param description The description of the event.
     * @param isDone Whether the event is done.
     * @param at When the event will be at.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.setAtDateAndTime();
    }

    /**
     * Sets the date or datetime object of when this event will be upon initializing it, based on the String "at" input.
     * If the input cannot be parsed, no action will be done.
     * To set just the date, the format of "at" has to be "YYYY-MM-DD" e.g. "2019-03-04".
     * To set both the datetime and date, the format of "at" has to be "YYYY-MM-DD'T'HH:mm:ss" e.g. "2019-03-04T00:05:02".
     */
    private void setAtDateAndTime() {
        try {
            this.atDateTime = LocalDateTime.parse(this.at);
            this.atDate = this.atDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                this.atDate = LocalDate.parse(this.at);
            } catch (DateTimeParseException ignored) { }
        }
    }

    public String getAt() {
        return this.at;
    }

    private String getFormattedAt() {
        if (this.atDateTime != null) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"));
        } else if (this.atDate != null) {
            return this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.at;
        }
    }

    /**
     * Returns whether this event is happening on a particular date.
     * @param date The date.
     * @return Whether this event is on the date input.
     */
    public boolean isOnDate(LocalDate date) {
        if (this.atDate != null) {
            return date.compareTo(this.atDate) == 0;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedAt() + ")";
    }
}