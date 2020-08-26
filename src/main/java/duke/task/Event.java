package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task which is an Event.
 */
public class Event extends Task {

    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    /**
     * Creates an Event Task.
     * @param description Description of Event.
     * @param at The date and time during which the Event occurs.
     */
    public Event(String description, String at) {
        super(description);
        String[] startEnd = at.split(" to ");
        LocalDateTime timeStart;
        LocalDateTime timeEnd;
        try {
            timeStart = LocalDateTime.parse(startEnd[0],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            timeEnd = LocalDateTime.parse(startEnd[1],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException ignored) {
            return;
        }
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        String at = timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the text representation of an Event to be saved in a text file.
     *
     * @return Text representation of Event.
     */
    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        String at = timeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E" + separator + isDone + separator + super.description + separator + at + "\n";
    }
}
