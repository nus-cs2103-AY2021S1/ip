package duke.task;

import duke.dukeException.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;
    protected DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd/MM/yy");
    protected DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Constructor of a Deadline task with description, Date and/or Time and task status indicating
     * whether it is completed.
     *
     * @param description Description of Deadline task.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            String dateTimeSplit[] = by.split(" ");
            if (dateTimeSplit.length <= 1) {
                time = null;
            } else {
                time = LocalTime.parse(dateTimeSplit[1], timeParser);
            }
            date = LocalDate.parse(dateTimeSplit[0], dateParser);
        } catch (DateTimeParseException e) {
            throw new DukeException("Yo! DateTime format is wrong. <dd/MM/yy [HH:mm]>");
        }
    }

    /**
     * A method to display Deadline object attributes in String format.
     *
     * @return Deadline task attributes in a string.
     */
    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + dateParser.format(date) + ")";
        } else {
            return "[D]"
                    + super.toString()
                    + " (by: "
                    + dateParser.format(date)
                    + " "
                    + timeParser.format(time)
                    + ")";
        }
    }

    /**
     * A method to display Deadline object attributes in String format for the save file.
     *
     * @return Event task attributes in a string for the save file.
     */
    @Override
    public String toFile() {
        return "D | " + getStatusCode() + " | " + description + " | " + by;
    }

    /**
     * A method to retrieve LocalDate from Deadline object
     *
     * @return Java Optional instance of LocalDate
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.of(date);
    }
}
