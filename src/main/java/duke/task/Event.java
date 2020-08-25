package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String at;

    /**
     * Returns the date and time of the event.
     *
     * @return The date and time of the event.
     */
    public String getAt() {
        return at;
    }

    /**
     * Initializes an event task with its description, LocalDate and LocalTime for
     * the date and time of event.
     *
     * @param description The description of the event.
     * @param date The date of the event.
     * @param time The time of the event.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        this.at = convertDateAndTimeToString();
    }


    String convertDateAndTimeToString() {
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + time;
        return str;
    }

    /**
     * Returns the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getLocalDate() {
        return date;
    }

    /**
     * Returns the time of the event.
     *
     * @return The time of the event.
     */
    public LocalTime getLocalTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
