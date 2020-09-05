package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles event-based Tasks.
 */

public class Event extends DatedTask {
    /**
     * Constructor for duke.Event-based Tasks.
     * @param name Description of duke.Task.
     * @param date Date of duke.Task.
     */
    public Event(String name, LocalDate date) {
        super(name, date);
    }

    /**
     * Constructor for duke.Event-based Tasks with Tags.
     * @param name Description of duke.Task.
     * @param deadline duke.Deadline of duke.Task.
     * @param tags Tags associated with Task.
     */
    public Event(String name, LocalDate deadline, String[] tags) {
        super(name, deadline, tags);
    }

    /**
     * Constructor for duke.Event-based Tasks with completed state.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public Event(String name, boolean isCompleted, LocalDate date) {
        super(name, isCompleted, date);
    }

    /**
     * Constructor for duke.Event-based Tasks with completed state and tags.
     * @param name Description of duke.Task.
     * @param isCompleted State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public Event(String name, boolean isCompleted, LocalDate date, String[] tags) {
        super(name, isCompleted, date, tags);
    }

    /**
     * Represents duke.Event in format to be saved.
     * @return Saved representation of duke.Event object.
     */
    @Override
    public String format() {
        return "E" + getDelimiter() + super.format();
    }

    /**
     * Represents duke.Event in String form.
     * @return String representation of duke.Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
