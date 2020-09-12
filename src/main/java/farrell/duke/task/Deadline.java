package main.java.farrell.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.java.farrell.duke.DukeException;

/**
 * Encapsulates data for a deadline task.
 */
public class Deadline extends TimedTask {
    /**
     * Creates a Deadline with a description and deadline date, marked as incomplete.
     * @param description Description of the task.
     * @param time Deadline date.
     */
    public Deadline(String description, LocalDate time) {
        this(description, false, time);
    }

    /**
     * Creates a Deadline with a description, completion status, and deadline date.
     * @param description Description of the task.
     * @param isDone Completion status.
     * @param time Deadline date.
     */
    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
        taskType = TaskType.DEADLINE;
    }

    /**
     * Creates a Deadline from some formatted saved data.
     * @param data The data to convert.
     * @return The resulting Deadline object.
     * @throws DukeException If the saved data is invalid.
     */
    public static Deadline fromData(String[] data) throws DukeException {
        try {
            return new Deadline(data[2], Boolean.parseBoolean(data[1]), LocalDate.parse(data[3]));
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

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
