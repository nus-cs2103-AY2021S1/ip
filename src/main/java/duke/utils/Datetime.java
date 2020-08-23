package duke.utils;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a DateTime object.
 * This class also supports the formatting and construction of LocalDateTime objects.
 */
public class Datetime {
    private final LocalDateTime datetime;
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;

    /**
     * Constructor method.
     * @param datetime the actual LocalDateTime object.
     * @param inputPattern the String format that the LocalDateTime object is originally read in as.
     * @param outputPattern the String format the LocalDateTime object is to be printed out as.
     */
    public Datetime(LocalDateTime datetime, String inputPattern, String outputPattern) {
        this.datetime = datetime;
        this.inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        this.outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    }

    /**
     * Converts the datetime attribute to the format specified by outputFormatter.
     * @return the formatted date time String.
     */
    public String getOutputDatetimeString() {
        return this.outputFormatter.format(this.datetime);
    }

    /**
     * Parses the datetime String, with its pattern to a LocalDateTime object.
     * @param datetime the String that is to be parsed.
     * @param pattern the specified format of the datetime String.
     * @return the parsed LocalDateTime object.
     * @throws DukeException if the datetime String does not match the pattern String.
     */
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

    /**
     * Parses the time String into a LocalDateTime object.
     * The time is converted into a LocalTime object first, and then converted into a LocalDateTime object
     * with the date of today.
     * @param time the String that is to be parsed.
     * @param pattern the specified format of the time String.
     * @return the parsed LocalDateTime object.
     * @throws DukeException if the time String does not match the pattern String.
     */
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
