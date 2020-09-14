package nite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import nite.exception.NiteException;

/**
 * Represents a Task which is an Event.
 */
public class Event extends Task {

    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;

    /**
     * Creates an Event Task.
     * @param description Description of Event.
     * @param at The date and time during which the Event occurs.
     * @throws NiteException If format of time is wrong.
     */
    public Event(String description, String at) throws NiteException {
        super(description);
        String[] startEnd = at.split(" to ");
        LocalDateTime timeStart;
        LocalDateTime timeEnd;
        try {
            timeStart = LocalDateTime.parse(startEnd[0], super.inputDateTimePattern);
            timeEnd = LocalDateTime.parse(startEnd[1], super.inputDateTimePattern);
        } catch (DateTimeParseException ex) {
            throw new NiteException("Wrong format of for deadline time.");
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
        String timeInterval = timeStart.format(super.displayDateTimePattern) + " to "
                + timeEnd.format(super.displayDateTimePattern);
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
        String timeInterval = timeStart.format(super.inputDateTimePattern) + " to "
                + timeEnd.format(super.inputDateTimePattern);
        return String.format("E%s%s%s%s%s%s%n", separator, isDone, separator,
                super.description, separator, timeInterval);
    }

    /**
     * Returns the type of the Event Task.
     *
     * @return String representing Event.
     */
    @Override
    public String typeOfTask() {
        return "event";
    }

    public LocalDateTime getTime() {
        return timeStart;
    }
}
