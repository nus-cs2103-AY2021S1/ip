package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event to be held on a certain date.
 */
public class Event extends Task {
    /** The date of the event */
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
        // Split into type of task, status, description and event date
        String[] splitEventDetails = eventDetails.split("\\|", 4);

        String status = splitEventDetails[1];
        String description = splitEventDetails[2];
        LocalDate eventDate = LocalDate.parse(splitEventDetails[3]);
        Event event = new Event(description, eventDate);

        if (status.equals("true")) {
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
