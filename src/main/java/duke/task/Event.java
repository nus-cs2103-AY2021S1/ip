package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents an event task that extends from the Task class, which consists of a description and a date.
 */
public class Event extends Task {
    private String eventDate;
    private LocalDate eventLocalDate;
    private String formatEventDate;

    /**
     * Constructs a new Event object.
     * @param taskName name of the Event.
     * @param eventDate date of the Event.
     * @throws DukeException if the date provided is of the wrong format.
     */
    public Event(String taskName, String eventDate) throws DukeException {
        super(taskName, "E");
        try {
            this.eventDate = eventDate;
            this.eventLocalDate = LocalDate.parse(eventDate);
            this.formatEventDate = this.eventLocalDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (Exception e) {
            throw new DukeException("Please use YYYY-MM-DD format for dates~");
        }
    }

    /**
     * Gets the date at which the Event is occurring.
     * @return a string representing the date in DD-MMM-YYYY format.
     */
    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName + " (at:" + formatEventDate + ")";
    }
}
