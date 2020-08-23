package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline task which contains a valid LocalDate.
 */
public class Deadline extends Task {

    /**
     * Creates a deadline task with a description and deadline date.
     * @param description The task to complete by deadline.
     * @param by The date that the task has to be completed.
     * @throws DukeException If date format entered is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    /**
     * Creates a completed deadline task with description and date. Used only when loading
     * data to the program.
     * @param description The task that is completed.
     * @param by The date which should have passed.
     * @param isDone The task completion status.
     * @throws DukeException If date format is invalid.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description);
        this.isDone = isDone;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

    @Override
    public String saveData() {
        return "D > " + super.saveData() + " > by: " + this.date;
    }

    @Override
    public String toString() {
        return "D > " + super.toString() + " > by: " + printDate();
    }

}
