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
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates an Event instance with task description, date and time.
     *
     * @param task Task description.
     * @param date Date of event.
     * @param time Time of event.
     */
    public Event(String task, LocalDate date, LocalTime time) {
        super(task);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns an Event with the appropriate details. Should only be used when testing or reading from
     * the storage file.
     *
     * @param task Task description.
     * @param inputDate String representing the date in the format YYYY-MM-DD
     * @param inputTime String representing the time in the format HH:MM(:SS)
     * @param isDone Status of the task.
     * @return A Deadline instance representing the deadline.
     */
    public static Event of(String task, String inputDate, String inputTime, boolean isDone) {
        try {
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime time = LocalTime.parse(inputTime, DateTimeFormatter.ISO_LOCAL_TIME);
            Event event = new Event(task, date, time);
            if (isDone) {
                event.setDone();
            }
            return event;
        } catch (DateTimeException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Gets the task type.
     *
     * @return type of task in String.
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    LocalDate getDate() {
        return date;
    }

    /**
     * Returns a String representation of the event to be stored in the
     * storage file.
     *
     * @return Formatted String representing the deadline.
     */
    @Override
    public String toDataString() {
        return "E // " + (isDone ? "1" : "0") + " // " + task + " // "
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
