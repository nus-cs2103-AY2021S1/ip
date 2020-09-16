package luke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event and its time for the user.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates a Event object that indicates the task and its time.
     *
     * @param description details about the task
     * @param at time that the event takes place
     */
    public Event(String description, LocalDate at) {
        super(TaskType.EVENT, description);
        this.at = at;
    }

    public String getAt() {
        return stringifyAt();
    }

    private String stringifyAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
    }

    @Override
    public String toDataString() {
        return String.format("E|%s|%s", super.toDataString(), this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }
}
