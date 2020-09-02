package src.main.java.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class that represents a event task.
 *
 */
public class Event extends Task {
    private LocalDateTime eventDate;

    /**
     * Constructor for event with description and duedate
     * @param description description of the event
     * @param eventDate duedate of the event
     */
    public Event(String description, String eventDate) {
        this.description = description;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.eventDate = LocalDateTime.parse(eventDate, dateFormat);
    }

    /**
     * Gets a string that print event task.
     * @return string that represents event task
     */
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + eventDate.format(dateFormat) + ")";
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    /**
     * Gets the string to be written for the event.
     * @return string which is to be written for the event
     */
    public String toWriteString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E " + super.toWriteString() + " | " + eventDate.format(dateFormat);
    }
}
