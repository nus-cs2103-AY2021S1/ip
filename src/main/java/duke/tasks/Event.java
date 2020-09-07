package duke.tasks;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** This class represents an event. */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructs an Event object associated with a description and a date.
     * @param description The description of the task.
     * @param date The date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs an Event object associated with a description, a date and a start time.
     * @param description The description of the task.
     * @param date The date of the event.
     * @param startTime The time that the event starts.
     */
    public Event(String description, LocalDate date, LocalTime startTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
    }

    /**
     * Constructs an Event object associated with a description, a date, a start time and a end time.
     * @param description The description of the task.
     * @param date The date of the event.
     * @param startTime The time that the event starts.
     * @param endTime The time that the event ends.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        assert this.date != null : "date parameter of Event object cannot be null";
        return this.date;
    }

    /**
     * @return A String representing the Event object, to be used when saving events to the storage file.
     */
    @Override
    public String txtFileFormat() {
        return "E" + DELIMITER + super.txtFileFormat() + DELIMITER + this.date.toString()
                + (startTime != null ? DELIMITER + startTime.toString() : "")
                + (endTime != null ? DELIMITER + endTime.toString() : "");
    }

    /**
     * @return A String representing the Event object, to be used when printing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + (startTime != null ? " " + startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "")
                + (endTime != null ? "-" + endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "") + ")";
    }

    @Override
    public void updateDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public void updateTime(LocalTime time) {
        this.startTime = time;
        this.endTime = null;
    }

    @Override
    public void updateTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
