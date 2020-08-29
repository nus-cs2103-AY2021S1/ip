package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class to store event information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Events extends Task{
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    /**
     * Event constructor to initialize a event object with the name and time
     * @param name name of event
     * @param time date and time of event in the form of a string
     */
    Events(String name, String time) {
        super(name,time);
        String[] at = time.split(" ");
        this.date = parseDate(at[1]);
        String[] timeArray = at[2].split("-");
        this.startTime = parseTime(timeArray[0]);
        this.endTime = parseTime(timeArray[1]);
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
        return String.format("%s, %s to %s",
                formatDate.format(date),
                formatTime.format(startTime),
                formatTime.format(endTime));

    }

    /**
     * toString method which converts the object to a String
     * @return String
     */
    @Override
    public String toString(){
        if (super.completed) {
            return "[E]" + "[" + "✓" + "] " + name + "(at: " + printDateTime()  +")";
        } else {
            return "[E]" + "[" + "✗" + "] " + name + "(at: " + printDateTime()  +")";
        }

    }

}
