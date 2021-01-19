package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a particular type of Task, corresponding to tasks of the "Event" form
 */
public class Event extends Task {
    private String eventDateString;
    private LocalDate eventDate = null;

    /**
     * Object representing Event tasks
     * @param taskName Name of Event task
     * @param eventTime String representing time of event. Will be specially formatted if it's a valid date format
     */
    public Event(String taskName, String eventTime) {
        super(taskName);
        try {
            eventDate = LocalDate.parse(eventTime);
            this.eventDateString = eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            this.eventDateString = eventTime;
        }
    }

    /**
     * Gets a String which represents the Event in the appropriate format for storing to the hard disk
     * @return String in the storage format representing Event
     */
    @Override
    public String getStorageFormat() {
        return "E | " + super.getStorageFormat() + " | " + eventDateString;
    }

    /**
     * Provides string representation of Event, used for UI display
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDateString + ")";
    }
}
