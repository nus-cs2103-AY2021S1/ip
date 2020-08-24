package duke.task;

import java.time.LocalDate;
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
     * Instantiates a new Event.
     *
     * @param description the description.
     * @param date        the date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description, TaskType.EVENT);
        this.date = date;
    }

    /**
     * Instantiates a new Event.
     *
     * @param description the description.
     * @param date        the date of the event.
     * @param isDone      true if event is done, false otherwise.
     */
    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone, TaskType.EVENT);
        this.date = date;
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
     * Returns the formatted date.
     *
     * @return the formatted date.
     */
    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

    /**
     * Returns data of the event.
     * Used to store the event.
     *
     * @return the data.
     */
    @Override
    public String getData() {
        return String.format("%s_%s ", super.getData(), date);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
