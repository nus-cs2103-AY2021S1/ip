package main.java.farrell.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.java.farrell.duke.DukeException;

/**
 * Encapsulates data for an event task
 */
public class Event extends TimedTask {
    /**
     * Creates an Event with a description and occurrence date, marked as incomplete.
     *
     * @param description Description of the task.
     * @param time Occurrence date.
     */
    public Event(String description, LocalDate time) {
        this(description, false, time);
    }

    /**
     * Creates an Event with a description, completion status, and occurrence date.
     * @param description Description of the task.
     * @param isDone Completion status.
     * @param time Occurrence date.
     */
    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.EVENT;
    }

    /**
     * Creates an Event from some formatted saved data.
     *
     * @param data The data to convert.
     * @return The resulting Event object.
     * @throws DukeException If the saved data is invalid.
     */
    public static Event fromData(String[] data) throws DukeException {
        try {
            return new Event(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3]));
        } catch (Exception exception) {
            throw new DukeException("Saved data is invalid!");
        }
    }

    @Override
    public String convertToData() {
        return taskType.name() + "|"
                + (isDone ? "true" : "false") + "|"
                + description + "|"
                + time.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
