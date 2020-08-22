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
     * Constructor for duke.Event-based Tasks.
     * @param name Description of duke.Task.
     * @param completed State of completion of duke.Task.
     * @param date Date of duke.Task.
     */
    public Event(String name, boolean completed, LocalDate date) {
        super(name, completed, date);
    }

    /**
     * Represents duke.Event in format to be saved.
     * @return Saved representation of duke.Event object.
     */
    @Override
    public String format() {
        return "E" + SAVE_DELIMITER + super.format();
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
