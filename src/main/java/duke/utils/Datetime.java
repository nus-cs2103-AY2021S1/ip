package duke.utils;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Datetime {
    private final LocalDate datetime;
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;

    public Datetime(LocalDate datetime, String inputPattern, String outputPattern) {
        this.datetime = datetime;
        this.inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        this.outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    }

    public String getOutputDatetimeString() {
        return this.outputFormatter.format(this.datetime);
    }

    public static LocalDate parseDateTimeString(String datetime, String pattern)
            throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(datetime, dtf);
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the datetime passed in is of the form: '%s'.", pattern);
            throw new DukeException(msg);
        }
    }
}
