package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task of "event" category
 */


public class Event extends Task {

    protected LocalDate dateAt;

    public Event(int category, int status, String command, String date) {
        super(category, status, command);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateAt = LocalDate.parse(date, inputFormat);
    }

    /**
     * Prints out the event task and converts the date format
     * @return string representation
     */

    @Override
    public String toString() {
        String date = dateAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + "(at: " + date + ")";
    }
}