package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates methods and information that relate to an event task.
 */
public class Event extends Task {

    /** Date in which the event task occurs. */
    private final LocalDate eventDate;
    /** Time of the event task. */
    private final LocalTime eventTime;

    /**
     * Creates and initialises a new Event object.
     *
     * @param description Description of the event task.
     * @param eventDate LocalDate object that stores the date of the event task.
     * @param eventTime LocalTime object that stores the time the event task is arranged for.
     */
    public Event(String description, LocalDate eventDate, LocalTime eventTime) {
        super(description);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Returns the date of this event task.
     *
     * @return LocalDate object that stores the date of the event task.
     */
    @Override
    public LocalDate getDate() {
        return this.eventDate;
    }

    /**
     * Converts the event object into a string for storage in a file.
     *
     * @return String containing the relevant information of this event object to be saved in a file.
     */
    @Override
    public String convertTaskToFileString() {
        return "E | " + (hasBeenCompleted() ? "1 | " : "0 | ") + getDescription() + " | " + getTaskTag()
                + " | " + this.eventDate.toString() + " " + this.eventTime.toString();
    }

    /**
     * Converts the event object into a string to be displayed.
     *
     * @return String representation of this event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EE, MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedEventTime = this.eventDate.format(dateFormatter)
                + ", " + this.eventTime.format(timeFormatter);
        return "[E]" + super.toString() + " (at: " + formattedEventTime + ")";
    }
}
