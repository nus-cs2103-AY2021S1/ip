package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate eventTime;

    /**
     * Event constructor
     *
     * @param description Description string without date
     * @param eventTime Time of event LocalDate object
     * @param isDone Completion status of event
     */
    public Event(String description, LocalDate eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.eventTime.format(formatter) + ")";
    }
}
