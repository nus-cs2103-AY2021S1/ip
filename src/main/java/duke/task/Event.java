package duke.task;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that extends from the Task class, which consists of a description and a date.
 */
public class Event extends Task {
    private String eventDate;
    private LocalDate eventLocalDate;
    private String formatEventDate;

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
     * A function to get the date at which the Event is occurring.
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
