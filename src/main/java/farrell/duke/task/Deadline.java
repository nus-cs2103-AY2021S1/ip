package main.java.farrell.duke.task;

import main.java.farrell.duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates data for a deadline task.
 */
public class Deadline extends TimedTask {
    public Deadline(String description, LocalDate time) {
        this(description, false, time);
    }

    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.DEADLINE;
    }

    public static Deadline fromData(String[] data) throws DukeException {
        try {
            return new Deadline(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3]));
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

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
