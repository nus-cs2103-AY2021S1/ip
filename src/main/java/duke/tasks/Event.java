package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task saved by the user.
 */
public class Event extends Task {

    private String eventDate;

    /**
     * Creates an instance of a Event object with the appropriate
     * event name and date of event given in non standard format.
     *
     * @param eventName Description of task.
     * @param eventDate Date of event.
     */
    public Event(String eventName, String eventDate) {
        super(eventName);
        this.eventDate = eventDate;
    }

    /**
     * Creates an instance of a Event object with the appropriate
     * event name and date of event given in standard format.
     *
     * @param eventName Description of task.
     * @param localDate Date of event as a LocalDate object.
     */
    public Event(String eventName, LocalDate localDate) {
        super(eventName, true, localDate);
    }

    /**
     * Returns description of event.
     *
     * @return Description of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + (super.hasDate() ? localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventDate) + ")";
    }

    /**
     * Converts event to a savable format.
     *
     * @return Savable format of event.
     */
    @Override
    public String getSaveFormat() {
        return "E" + " | " + super.getSaveFormat() + " | " + (super.hasDate() ? localDate : eventDate);
    }
}
