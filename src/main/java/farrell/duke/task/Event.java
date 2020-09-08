package main.java.farrell.duke.task;

import main.java.farrell.duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates data for an event task
 */
public class Event extends TimedTask {
    public Event(String description, LocalDate time) {
        this(description, false, time);
    }

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.EVENT;
    }

    public static Event fromData(String[] data) throws DukeException {
        try {
            return new Event(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3]));
        } catch (Exception exception) {
            throw new DukeException("Saved data is invalid!");
        }
    }

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
