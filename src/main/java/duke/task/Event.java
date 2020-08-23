package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an event task which contains a valid LocalDate.
 */
public class Event extends Task{

    /**
     * Creates an event task with description and attendance date.
     * @param description The task details.
     * @param at The attendance date.
     * @throws DukeException If date format entered is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    /**
     * Creates a completed event task with description and attendance date. Used only when
     * loading data to program.
     * @param description The task details.
     * @param at The attendance date.
     * @param isDone The task completion status.
     * @throws DukeException If date format is invalid.
     */
    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description);
        this.isDone = isDone;
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    @Override
    public String saveData() {
        return "E > " + super.saveData() + " > at: " + this.date;
    }

    @Override
    public String toString() {
        return "E > " + super.toString() + " > at: " + printDate();
    }

}
