package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that contains both task name and a date to serve as the event date.
 * Time can be added as an optional parameter.
 */
public class Event extends Task {

    /** LocalDate object to store the date of the event. */
    private LocalDate date;

    /** LocalTime object to store the time of the event. */
    private LocalTime time;

    /**
     * Creates an Event task containing the description, date and time of the task.
     *
     * @param name Description of the event task.
     * @param date Date of the event.
     * @param time Time of the event.
     */
    public Event(String name, LocalDate date, LocalTime time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    /**
     * Creates an Event task containing the description and date of the task.
     *
     * @param name Description of the deadline task.
     * @param date Date of the task deadline.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.time = null;
        this.date = date;
    }

    /**
     * Returns the string representation of an Event task to be appended to a local file.
     *
     * @return String representation of an Event task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() == true ? "1" : "0");
        String time = this.time != null ? this.time.format(DateTimeFormatter.ofPattern("HHmm")) : "";
        return "event" + " | " + doneString + " | " + getName() + " | " + this.date + " | " + time;
    }

    /**
     * Returns the string representation of an Event task.
     *
     * @return String representation of an Event task containing the description, done status, formatted date and/or
     * time.
     */
    @Override
    public String toString() {
        String doneString = (isDone() == true ? "✓" : "✗");
        String dateFormat = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + "[" + doneString + "] " + getName() + " (at: " + dateFormat
                + (this.time != null ? " " + this.time.format(DateTimeFormatter.ofPattern("HHmma")) + " " : "") + ")";
    }
}
