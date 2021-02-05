package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Represents an Event</h1>
 * Contains the details of the event, whether it is completed,
 * and the date of the event.
 */
public class Event extends Task {
    private String date;
    private LocalDate localDate;

    /**
     * Creates an Event object.
     *
     * @param task Event details.
     * @param date Date of event.
     * @param isCompleted True if event is completed, false otherwise.
     * @param priority Priority level of the event, if any.
     */
    public Event(String task, String date, boolean isCompleted, int priority) {
        super(task, isCompleted, priority);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    public String getDate() {
        return date;
    }

    /**
     * An overridden method that returns a String with the Task type,
     * event details, event date formatted in MM dd yyyy format,
     * and priority level of deadline, if any.
     *
     * @return String with all details of the event.
     */
    @Override
    public String toString() {
        String text = "[E]" + super.toString() + " (at: "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        if (getPriority() != 0) {
            return text + " (priority: " + getPriority() + ")";
        } else {
            return text;
        }
    }
}
