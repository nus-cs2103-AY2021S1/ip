package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents an Event that is accompanied by a specific date
 * and time of occurrence.
 */
public class Event extends Task {

    /** Date of the occurrence of the Event */
    protected LocalDate date;

    /** Time of the occurrence of the Event */
    protected LocalTime time;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    /**
     * Creates an Event which has not been marked as done.
     *
     * @param description Details of the Event.
     * @param dateTime Date and time of the occurrence of the Event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    /**
     * Creates an Event.
     *
     * @param description Details of the Event.
     * @param isDone Boolean value that indicates if the Task has been completed.
     * @param dateTime Date and time of the occurrence of the Event.
     */
    public Event(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    /**
     * Returns the String representation of the Event to be
     * displayed on the UI.
     *
     * @return String representation of the Event to be
     * displayed on the UI.
     */
    @Override
    public String toDisplayString() {
        return "[E]" + super.toDisplayString() + " (at: "
                + date.format(DATE_FORMAT) + " "
                + time.format(TIME_FORMAT) + ")";
    }

    /**
     * Returns the String representation of the Event to be
     * displayed in the storage of the task list.
     *
     * @return String representation of the Event to be
     * displayed in the storage of the task list.
     */
    @Override
    public String toSavedString() {
        return "E" + " | " + super.toSavedString() + " | "
                + date.format(DATE_FORMAT) + " "
                + time.format(TIME_FORMAT);
    }

}
