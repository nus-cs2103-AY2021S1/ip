package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents an event task. */
public class Event extends Task {

    /** The date which the event is happening at. */
    private LocalDate date;

    /** Constructs a new Event object with the specified description and date.
     *
     * @param name The description of the task.
     * @param date The date which the event is happening at.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /** Constructs a new Event object with the specified indicator, description and date.
     *
     * @param isDone The indicator of whether the task is done.
     * @param name The description of the task.
     * @param date The date which the event is happening at.
     */
    public Event(boolean isDone, String name, LocalDate date) {
        super(isDone, name);
        this.date = date;
    }

    /**
     * @return The date which the event is happening at.
     */
    public LocalDate getDate() {
        return date;
    }

    /** Returns the string representation of the task. */
    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
