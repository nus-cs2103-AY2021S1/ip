package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * An extension of the Task class with an additional field specifying when the event
 * is to happen.
 */
public class Event extends Task {

    /** The date/time of the task or event. */
    protected String at;
    /** The date of the task or event parsed as a LocalDate. */
    protected LocalDateTime eventTime;

    /**
     * Constructs a new Event object.
     *
     * @param description {@inheritDoc}
     * @param at The date/time of the task or event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            eventTime = LocalDateTime.parse(at).truncatedTo(ChronoUnit.MINUTES);
        } catch (DateTimeParseException e) {
            try {
                eventTime = LocalDate.parse(at).atTime(0, 1);
            } catch (DateTimeParseException e1) {
                // Do nothing, since there is no deterministic way to convert all possible
                // strings into a LocalDateTime object meaningfully without adding more
                // prohibitions to the user input.
            }
        }
    }

    /**
     * Converts the event time to the format "%B %d %Y HH:MM", such as JANUARY 1 2000 12:34, if available.
     *
     * @return The event time in the desired format if parsable; the original string otherwise.
     */
    public String getAt() {
        if (eventTime == null) {
            return at;
        }
        return String.format("%s %02d %d %02d:%02d",
                eventTime.getMonth().toString(),
                eventTime.getDayOfMonth(),
                eventTime.getYear(),
                eventTime.getHour(),
                eventTime.getMinute());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        int stat = 0;
        if (isDone) {
            stat = 1;
        }
        return String.format("%s | %d | %s at %s", getType(), stat, description, getAt());
    }
}
