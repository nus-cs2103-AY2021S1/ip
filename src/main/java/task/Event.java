package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an Event which is a Task that will be happening on a certain Date.
 */
public class Event extends Task {

    private LocalDateTime eventTime;

    /**
     * Creates an Event instance containing a description and a Date.
     *
     * @param description Description of Event to be done.
     * @param eventTime Date on which the event will be happening.
     */
    public Event(String description, LocalDateTime eventTime) {
        this(description, eventTime, false);
    }

    /**
     * Creates an Event instance containing a description and a Date.
     *
     * @param description Description of Event to be done.
     * @param eventTime Date on which the event will be happening.
     * @param isDone True if the Event is already done otherwise false.
     */
    public Event(String description, LocalDateTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Format in which the Event should be saved in Storage.
     *
     * @return String format of the Event for saving in Storage.
     */
    @Override
    public String getSavingFormat() {
        return "E" + "~" + super.getSavingFormat() + "~" + this.eventTime;
    }

    /**
     * Checks whether the Event has the same Date as specified.
     *
     * @param theDate Date to check whether the Event has occurred on.
     * @return True if the Event occurred on the specified date otherwise false.
     */
    public boolean hasSameDate(LocalDate theDate) {
        assert theDate != null : "theDate should not be null";
        return eventTime.toLocalDate().equals(theDate);
    }

    /**
     * Returns the String representation of the Event to be displayed to the user.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DATE_FORMAT) + ")";
    }
}
