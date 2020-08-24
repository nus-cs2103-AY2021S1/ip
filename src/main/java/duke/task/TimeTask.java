package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

public abstract class TimeTask extends Task {
    private final LocalDate date;
    public abstract String getDateDescriptor();
    public TimeTask(String description, String dateString) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw DukeException.Errors.DATE_PARSE_ERROR.create();
        }
    }

    protected String dateString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return super.toString()
                + " ("
                + this.getDateDescriptor()
                + ": "
                + this.dateString()
                + ")";
    }
}
