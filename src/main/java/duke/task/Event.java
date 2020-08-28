package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class contains the name, completion status
 * and duration of a task.
 */
public class Event extends Task {
    protected LocalDate duration;

    /**
     * Constructor for an Event object.
     *
     * @param name The name of the event.
     * @param duration The duration of the event.
     */
    public Event(String name, LocalDate duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Returns the duration of an Event object.
     *
     * @return A LocalDate which represents the duration of the event.
     */
    public LocalDate getDuration() {
        return duration;
    }

    /**
     * Returns a string representation of an Event object.
     *
     * @return A String which contains the name, completion
     * status and the duration of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}