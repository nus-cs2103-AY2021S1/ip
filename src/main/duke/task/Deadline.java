package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    /**
     * Creates new Deadline task.
     *
     * @param msg Stored message for deadline.
     * @param date Date of deadline.
     * @param time Time of deadline (empty string or "NA" for no time).
     */
    public Deadline(String msg, String date, String time){
        super(msg);
        this.date = LocalDate.parse(date);

        if (!time.equals("") && !time.equals("NA")) {
            this.time = LocalTime.parse(time);
        }
    }

    /**
     * Returns date of deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns time of deadline.
     *
     * @return Time of deadline.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a formatted string representing the deadline.
     *
     * @return Formatted string of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ((time != null) ? " " + time.format(DateTimeFormatter.ofPattern("HH:mm")) : "") + ")";
    }
}
