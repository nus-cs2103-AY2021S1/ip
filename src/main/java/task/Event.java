package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that marks an event with a date and optional time.
 */
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
    public Event(String msg, String date, String time) {
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
        assert date != null;
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

    @Override
    public String getDataString() {
        String timeString = (time != null) ? time.format(timeFormat) : "NA";
        return String.format("E | %s | %s | %s\n",
                super.getDataString(),
                date.format(dateFormat),
                timeString);
    }

    /**
     * Returns a formatted string representing the event.
     *
     * @return Formatted string of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((time != null) ? " " + time.format(DateTimeFormatter.ofPattern("HH:mm")) : "") + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Event) {

            Event event = (Event) obj;

            return super.equals(obj)
                    && this.date.equals(event.date)
                    && ((this.time == null && event.time == null)
                    || ((this.time != null && event.time != null)
                    && this.time.equals(event.time)));
        }
        return false;
    }
}
