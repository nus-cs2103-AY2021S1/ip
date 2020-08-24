package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Holds the deadline task and relative operations.
 * Inherits from Task class.
 */
public class Deadline extends Task {

    /** Stores Time(Date) information of the deadline object */
    protected LocalDate time;

    /**
     * Initializes a deadline object.
     * Sets the status to false(not done).
     * @param content Content of the deadline object.
     * @param time Time of the deadline object, in string form, will be parsed to LocalDate.
     */
    public Deadline(String content, String time) {
        super(content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Initializes a deadline object.
     * @param status Status of the deadline object.
     * @param content Content of the deadline object.
     * @param time Time of the deadline object, int string form, will be parsed to LocalDate.
     */
    public Deadline(boolean status, String content, String time) {
        super(status, content);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gives the time information of the deadline object.
     * In "yyyy-MM-dd" format, e.g. 2020-08-01.
     * @return The time in string type with the format "yyyy-MM-dd".
     */
    public String getTime() { return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    /**
     * Gives the string representation of the deadline object.
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + (super.status ? "[√] " : "[×] ") + super.content + "(by " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" + "  <-";
    }
}
