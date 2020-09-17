package duke.logic.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDeadlineFormatException;
import duke.exceptions.InvalidUpdateTaskDateException;

/**
 * Represents a deadline task that has a specific deadline.
 * A Deadline object is made up of the task name, as well as a date and time of the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime byDateTime;
    private String originalFormat;
    private String printedFormat;

    /**
     * Instantiates a Deadline object if the input was written in the correct format.
     *
     * @param taskName The description of the task.
     * @param date The date and time of the deadline.
     * @throws DukeException If the deadline is not specified in the correct format.
     */
    public Deadline(String taskName, String date) throws DukeException {
        super(taskName);

        try {
            this.byDateTime = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.byDateTime.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.byDateTime.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new InvalidDeadlineFormatException();
        }
    }

    @Override
    public void setTaskDateTime(String dateTime) throws DukeException {
        try {
            this.byDateTime = LocalDateTime.parse(
                    dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.originalFormat = this.byDateTime.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.printedFormat = this.byDateTime.format(
                    DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new InvalidUpdateTaskDateException();
        }
    }

    /**
     * Returns the string representation of the deadline task in the format to be saved in the computer.
     *
     * @return String representation of the task.
     */
    @Override
    public String toTaskData() {
        String separator = " ; ";
        return "D" + separator + super.toTaskData() + separator + originalFormat;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printedFormat + ")";
    }
}
