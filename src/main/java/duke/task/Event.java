package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event to be held on a certain date.
 */
public class Event extends Task {
    protected LocalDate eventDate;

    /**
     * Creates a new Event with the specified description and event date.
     *
     * @param description the description of the event.
     * @param eventDate the event date.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Loads previous event details and creates a new instance of the event.
     *
     * @param eventDetails the details of the event in 'saved' format.
     * @return a new instance of the event.
     */
    public static Event load(String eventDetails) {
        String[] splitEventDetails = eventDetails.split("\\|", 4);
        Event event = new Event(splitEventDetails[2], LocalDate.parse(splitEventDetails[3]));
        if (splitEventDetails[1].equals("true")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Returns the description and status of an event in a particular string format.
     * This format is used for saving events, i.e. 'saved' format.
     *
     * @return a string representation of the event in a 'saved' format.
     */
    @Override
    public String saveAs() {
        return "E | " + super.saveAs() + " | " + eventDate;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.format(
                DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
