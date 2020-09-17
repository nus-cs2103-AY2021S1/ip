package duke.logic.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidEventFormatException;
import duke.exceptions.InvalidUpdateTaskDateException;

/**
 * Represents an event task that has a specific start and end date and time.
 * An Event object is made up of the task name, as well as a date and time of the event.
 */
public class Event extends Task {
    protected LocalDateTime atDateTime;
    private String originalFormat;
    private String printedFormat;

    /**
     * Instantiates a Event object if the input was written in the correct format.
     *
     * @param taskName The description of the task.
     * @param date The date and time of the event.
     * @throws DukeException If the event is not specified in the correct format.
     */
    public Event(String taskName, String date) throws DukeException {
        super(taskName);

        try {
            this.atDateTime = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.atDateTime.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.atDateTime.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new InvalidEventFormatException();
        }
    }

    @Override
    public void setTaskDateTime(String dateTime) throws DukeException {
        try {
            this.atDateTime = LocalDateTime.parse(
                    dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.atDateTime.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.atDateTime.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new InvalidUpdateTaskDateException();
        }
    }

    /**
     * Returns the string representation of the event task in the format to be saved in the computer.
     *
     * @return String representation of the task.
     */
    @Override
    public String toTaskData() {
        String separator = " ; ";
        return "E" + separator + super.toTaskData() + separator + originalFormat;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printedFormat + ")";
    }
}
