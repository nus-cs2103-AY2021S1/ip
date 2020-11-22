package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that marks a deadline with a date and optional time.
 */
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
    public Deadline(String msg, String date, String time) {
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
        assert date != null;
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

    @Override
    public String getDataString() {
        String timeString = (time != null) ? time.format(timeFormat) : "NA";
        return String.format("E | %s | %s | %s\n",
                super.getDataString(),
                date.format(dateFormat),
                timeString);
    }

    /**
     * Returns a formatted string representing the deadline.
     *
     * @return Formatted string of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((time != null) ? " " + time.format(DateTimeFormatter.ofPattern("HH:mm")) : "") + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Deadline) {

            Deadline deadline = (Deadline) obj;

            return super.equals(obj)
                    && this.date.equals(deadline.date)
                    && ((this.time == null && deadline.time == null)
                    || ((this.time != null && deadline.time != null)
                    && this.time.equals(deadline.time)));
        }
        return false;
    }
}
