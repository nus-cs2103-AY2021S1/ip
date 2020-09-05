package blue.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event, a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /**
     * The Date.
     */
    private final LocalDate date;
    /**
     * the start time of the event.
     */
    private final LocalTime startTime;
    /**
     * the start time of the event.
     */
    private final LocalTime endTime;

    /**
     * Instantiates a new Event with start and end time.
     *
     * @param description the description.
     * @param date        the date of the event.
     * @param startTime   the start time of the event.
     * @param endTime     the end time of the event.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(description, TaskType.EVENT);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new Event with start and end time.
     *
     * @param description the description.
     * @param date        the date of the event.
     * @param startTime   the start time of the event.
     * @param endTime     the end time of the event.
     * @param isDone      true if event is done, false otherwise.
     */
    public Event(String description, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isDone) {
        super(description, isDone, TaskType.EVENT);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a boolean which is true if date is equal, false otherwise.
     *
     * @param date the date.
     * @return the boolean.
     */
    public boolean isDateEqual(LocalDate date) {
        return date.equals(this.date);
    }

    /**
     * Returns data of the event.
     * Used to store the event.
     *
     * @return the data.
     */
    @Override
    public String getData() {
        return String.format("%s_%s_%s_%s", super.getData(), this.date, this.startTime, this.endTime);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s, start: %s, end: %s)", super.toString(), this.getFormattedDate(),
                this.getFormattedTime(this.startTime), this.getFormattedTime(this.endTime));
    }

    /**
     * Returns the formatted date.
     *
     * @return the formatted date.
     */
    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.date.format(formatter);
    }

    /**
     * Returns the formatted time.
     *
     * @return the formatted time.
     */
    private String getFormattedTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        return time.format(formatter);
    }
}
