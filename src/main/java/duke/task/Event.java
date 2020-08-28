package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents an event.
 */
public class Event extends Task {
    LocalDate date;
    LocalTime time;

    public Event(String task, LocalDate date, LocalTime time) {
        super(task);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns an Event with the appropriate details. Should only be used when reading from
     * the storage file.
     *
     * @param task Task description.
     * @param inputDate String representing the date in the format YYYY-MM-DD
     * @param inputTime String representing the time in the format HH:MM(:SS)
     * @param done Status of the task.
     * @return A Deadline instance representing the deadline.
     */
    public static Event of(String task, String inputDate, String inputTime, boolean done) {
        try {
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime time = LocalTime.parse(inputTime, DateTimeFormatter.ISO_LOCAL_TIME);
            Event event = new Event(task, date, time);
            if (done) {
                event.setDone();
            }
            return event;
        } catch (DateTimeException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Returns a String representation of the event to be stored in the 
     * storage file.
     *
     * @return Formatted String representing the deadline.
     */
    @Override
    public String toDataString() {
        return "E // " + (done ? "1": "0") + " // " + task + " // " 
            + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " // "
            + time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Returns a String representation of the event for display. The date and
     * time substring is in the format Mmm DD, YYYY (H)H:MM AM/PM.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            + " " + this.time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
            + ")";
    }
}
