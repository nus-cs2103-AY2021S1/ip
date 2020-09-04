package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates events that are a subtype of Task and stores the name of the event and its date.
 */
public class Event extends Task {
    private LocalDate localDate;
    private String date;

    /**
     * Initializes an instance of Event.
     *
     * @param event Name of the task.
     * @param dateToFormat Date that the event will happen.
     */
    public Event(String event, String dateToFormat) {
        super(event);
        LocalDate formatDate = LocalDate.parse(dateToFormat);
        localDate = formatDate;
        String convertedDate = formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = convertedDate;
    }

    /**
     * Initializes an instance of Event for local storage.
     *
     * @param task Name of the event.
     * @param isDone Status of the event.
     * @param dateToFormat Date that the event will happen.
     */
    public Event(String task, boolean isDone, String dateToFormat) {
        super(task, isDone);
        date = dateToFormat;
    }

    /**
     * Date of event formatted.
     *
     * @return Formatted version of date.
     */
    public String getFormattedDate() {
        return date;
    }

    /**
     * Provides a string describing the Event class.
     *
     * @return description of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
