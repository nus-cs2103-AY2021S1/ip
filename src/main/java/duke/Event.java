package duke;

import java.time.LocalDate;
import java.time.LocalTime;

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

    /**
     * Creates an Event which has not been marked as done.
     *
     * @param description Details of the Event.
     * @param date Date of the occurrence of the Event.
     * @param time Time of the occurrence of the Event.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Creates an Event.
     *
     * @param description Details of the Event.
     * @param isDone Boolean value that indicates if the Task has been completed.
     * @param date Date of the occurrence of the Event.
     * @param time Time of the occurrence of the Event.
     */
    public Event(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
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
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
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
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

}
