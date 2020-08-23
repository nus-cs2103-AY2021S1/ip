package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Please enter the date in this format: yyyy-mm-dd");
        }
    }

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
