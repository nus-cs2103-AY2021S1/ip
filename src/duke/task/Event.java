package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
        }
    }

    public Event(String description, boolean isDone, String at) throws DukeException  {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Loading: Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
        }
    }

    @Override
    public String getPlainText() {
        return super.getPlainText() + " | " + at;
    }

    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}