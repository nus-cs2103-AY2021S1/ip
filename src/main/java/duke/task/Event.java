package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a Task which is an Event.
 */
public class Event extends Task {

    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    /**
     * Creates an Event Task.
     * @param description Description of Event.
     * @param at The date and time during which the Event occurs.
     * @throws DukeException If format of time is wrong.
     */
    public Event(String description, String at) throws DukeException {
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
            throw new DukeException("Wrong format of for deadline time.");
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
        String timeInterval = timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + timeInterval + ")";
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
        String timeInterval = timeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " to "
                + timeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("D%s%s%s%s%s%s%n", separator, isDone, separator,
                super.description, separator, timeInterval);
    }
}
