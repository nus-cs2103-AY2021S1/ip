package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class to store deadline information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Deadlines extends Task {

    /**
     * Deadline constructor to initialize a deadline object with the name and time
     * @param name name of deadline
     * @param dateTime date and time of deadline in the form of a string
     */
    Deadlines(String name, String dateTime) throws DukeException {
        super(name, dateTime, "");
    }

    /**
     * toString method which converts the object to a String
     * @return String
     */
    @Override
    public String toString() {
        if (super.getDone()) {
            return "[D]" + "[\u2714] " + super.getName() + " (by: " + printDateTime() + ")";
        } else {
            return "[D]" + "[\u2718] " + super.getName() + " (by: " + printDateTime() + ")";
        }
    }
}
