package duke.utils;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Datetime {
    private final LocalDateTime datetime;
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;

    public Datetime(LocalDateTime datetime, String inputPattern, String outputPattern) {
        this.datetime = datetime;
        this.inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        this.outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    }

    public String getOutputDatetimeString() {
        return this.outputFormatter.format(this.datetime);
    }

    public static LocalDateTime parseDateTimeString(String datetime, String pattern)
            throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(datetime, dtf);
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the datetime passed in is of the form: '%s'.", pattern);
            throw new DukeException(msg);
        }
    }

    public static LocalDateTime parseTimeString(String time, String pattern) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            LocalTime localTime = LocalTime.parse(time, dtf);
            return LocalDateTime.of(LocalDate.now(), localTime);
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the time passed in is of the form: '%s'.", pattern);
            throw new DukeException(msg);
        }
    }
}
