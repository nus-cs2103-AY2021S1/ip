package duke.task;

import duke.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that contains both task name and a date to serve as the event date.
 * Time can be added as an optional parameter.
 */
public class Event extends Task {

    /** DateTime object to store the date and any specified time of the event. */
    private DateTime dateTime;

    /**
     * Creates an Event task containing the description, date and time of the task.
     *
     * @param name Description of the event task.
     * @param dateTime Date and any specified time of the event.
     */
    public Event(String name, DateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of an Event task to be appended to a local file.
     *
     * @return String representation of an Event task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() == true ? "1" : "0");
        return "event" + " | " + doneString + " | " + getName() + " | " + dateTime.getFileFormattedDateTime();
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
        return "[E]" + "[" + doneString + "] " + getName() + " (at: " + dateTime.getPrintFormattedDate()
                + " " + dateTime.getPrintFormattedTime() + ")";
    }
}
