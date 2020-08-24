package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of tasks that has a time.
 */
public class Event extends Task {

    protected LocalDate at;
    private String atString;

    /**
     * Construct a new event task from specified description and due date.
     * @param description the description of the task to be created
     * @param at the string indicates the event time
     *           If the string <code>at</code> is of pattern that Duke understands, Duke will save the time as
     *           a date. Otherwise, Duke will understand this as a string.
     */
    public Event(String description, String at) {
        super(description);
        this.atString = at;
        try{
            this.atString = at;
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.at = null;
        }
    }

    /**
     * Return the string that is intended to be stored in the local database.
     * @return the string to be stored in the local database, the format is understandable for <code>Storage</code>
     */
    @Override
    public String toDataString() {
        if (super.isDone) return "E | 1 | " + description + " | " + atString;
        else return "E | 0 | " + description + " | " + atString;
    }

    /**
     * Return the string representation of the task.
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        if (this.at != null) return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        else return "[E]" + super.toString() + " (at: " + atString + ")";
    }
}
