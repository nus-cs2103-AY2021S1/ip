package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;

    /**
     * Creates new Event task.
     *
     * @param msg Stored message for event.
     * @param date Date of event.
     * @param time Time of event (empty string or "NA" for no time).
     */
    public Event(String msg, String date, String time){
        super(msg);
        this.date = LocalDate.parse(date);

        if (!time.equals("") && !time.equals("NA")) {
            this.time = LocalTime.parse(time);
        }
    }

    /**
     * Returns date of event.
     *
     * @return Date of event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns time of event.
     *
     * @return Time of event.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a formatted string representing the event.
     *
     * @return Formatted string of the event.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ((time != null) ? " " + time.format(DateTimeFormatter.ofPattern("HH:mm")) : "") + ")";
    }
}
