package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class to store deadline information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Deadlines extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Deadline constructor to initialize a deadline object with the name and time
     * @param name name of deadline
     * @param time date and time of deadline in the form of a string
     */
    Deadlines(String name, String time) {
        super(name,time);
        String[] by = time.split(" ");
        this.date = parseDate(by[1]);
        this.time = parseTime(by[2]);
    }

    /**
     * parseDate method which takes in a date in string form and converts it to a LocalDate object
     * @param dateString date in string format
     * @return returns the LocalDate
     */
    public LocalDate parseDate(String dateString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, format);
    }

    /**
     * parseTime method which takes in a time in string form and converts it to a LocalTime object
     * @param timeString time in string format
     * @return returns the LocalTime
     */
    public LocalTime parseTime(String timeString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, format);
    }

    /**
     * printDateTime method which takes in the date and time and converts it to String
     * @return returns String of date and time
     */
    public String printDateTime() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mma");
        return String.format("%s, %s",
                formatDate.format(date),
                formatTime.format(time));
    }

    /**
     * toString method which converts the object to a String
     * @return String
     */
    @Override
    public String toString() {
        if (super.isDone) {
            return "[D]" + "[" + "✓" + "] " + super.getName() + "(by: " + printDateTime() + ")";
        } else {
            return "[D]" + "[" + "✗" + "] " + super.getName() + "(by: " + printDateTime() + ")";
        }
    }
}
