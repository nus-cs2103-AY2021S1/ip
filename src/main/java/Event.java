package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Holds the Event objects and relative operations.
 * Inherits from Task class.
 */
public class Event extends Task {

    /** Stores time(date) information of the event object. */
    protected LocalDate time;

    /**
     * Initializes an event object.
     * Sets the status to false(not done).
     * @param content Content of the event object.
     * @param time Time of the event object, in string type, will be parsed to LocalDate type.
     */
    public Event(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Initializes an event object.
     * @param status Status of the event object.
     * @param content Content of the event object.
     * @param time Time of the event object, in string type, will be parsed to LocalDate type.
     */
    public Event(boolean status, String content, String time) {
        super(status, content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gives time information of the event object.
     * In "yyyy-MM-dd" format, e.g. 2020-08-01.
     * @return The time in string type with the format "yyyy-MM-dd".
     */
    public String getTime() { return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    /**
     * Gives the string representation of the event object.
     * @return String representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + (super.status ? "[√] " : "[×] ") + super.content + "(on " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "  <-";
    }
}
