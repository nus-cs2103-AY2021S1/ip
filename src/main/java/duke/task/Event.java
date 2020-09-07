package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    private final LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        this(description, dateTime, false);
    }

    /**
     * The event constructor.
     *
     * @param description The description of the event.
     * @param dateTime The date/time the event is due by.
     * @param isDone The boolean keeping track of whether the event is done.
     */
    public Event(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a boolean indicating if the event is happening at the given date.
     *
     * @param date A date.
     * @return true if the event is happening at the given date.
     */
    @Override
    public boolean isDue(LocalDate date) {
        return this.dateTime.toLocalDate().equals(date);
    }

    @Override
    public String toSaveData() {
        return "E | " + super.toSaveData() + " | " + this.dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

        return "[E]" + super.toString() + " (at: " + this.dateTime.format(formatter) + ")";
    }
}
