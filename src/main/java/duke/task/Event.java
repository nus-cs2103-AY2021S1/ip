package duke.task;

import duke.component.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate atDate;

    public Event(String description, String atDate) throws DukeException {
        super(description);
        try {
            this.atDate = LocalDate.parse(atDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date detected! Please enter date as yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String toStorageString() {
        if (super.isDone) return "E | 1 | " + description + " | " + atDate;
        else return "E | 0 | " + description + " | " + atDate;
    }

}
