package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Initiate Event object.
     * @param description description of event.
     * @param at time which event takes place.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Initiate Event object.
     * @param description description of event.
     * @param isDone specifies if event is completed.
     * @param time which event takes place.
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.at = time;
    }

    public LocalDate getAtWhen() {
        return this.at;
    }

    @Override
    public String toString() {
        String formattedTimeAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]" + super.toString() + " (at: %s)", formattedTimeAt);
    }
}
