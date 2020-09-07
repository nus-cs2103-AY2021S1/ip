package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeParseException;

/**
 * Represents a DateTime object.
 * This class also supports the formatting and construction of <code>LocalDateTime</code> objects.
 */
public class Datetime {
    private final LocalDateTime datetime;
    private final DateTimeFormatter inputFormatter;
    private final DateTimeFormatter outputFormatter;

    /**
     * Constructor method.
     *
     * @param datetime the actual <code>LocalDateTime</code>.
     * @param inputPattern the <code>String</code> format that <code>LocalDateTime</code>
     *                     is originally read in as.
     * @param outputPattern the <code>String</code> format <code>LocalDateTime</code>
     *                      is to be printed out as.
     */
    public Datetime(LocalDateTime datetime, String inputPattern, String outputPattern) {
        this.datetime = datetime;
        this.inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        this.outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
    }

    /**
     * Converts the datetime attribute to the format specified by <code>outputFormatter</code>.
     *
     * @return the formatted date time <code>String</code>.
     */
    public String getOutputDatetimeString() {
        return this.outputFormatter.format(this.datetime);
    }

    /**
     * Parses the datetime <code>String</code>, with its pattern to a <code>LocalDateTime</code>.
     *
     * @param datetime the <code>String</code> that is to be parsed.
     * @param pattern the specified format of the datetime <code>String</code>.
     * @return the parsed <code>LocalDateTime</code>.
     * @throws DukeParseException if the datetime <code>String</code> does not match the
     * pattern <code>String</code>.
     */
    public static LocalDateTime parseDateTimeString(
            String datetime, String pattern) throws DukeParseException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(datetime, dtf);
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the datetime passed in is of the form: '%s'.", pattern);
            throw new DukeParseException(msg);
        }
    }

    /**
     * Parses the date <code>String</code> into a <code>LocalDateTime</code>.
     * The date is converted into a <code>LocalDate</code> first,
     * and then converted into a <code>LocalDateTime</code> with the time of noon.
     *
     * @param date the <code>String</code> that is to be parsed.
     * @param pattern the specified format of the date <code>String</code>.
     * @return the parsed <code>LocalDateTime</code>.
     * @throws DukeParseException if the date <code>String</code> does not match
     * the pattern <code>String</code>.
     */
    public static LocalDateTime parseDateString(
            String date, String pattern) throws DukeParseException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            LocalDate localDate = LocalDate.parse(date, dtf);
            return LocalDateTime.of(localDate, LocalTime.of(12, 0));
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the date passed in is of the form: '%s'.", pattern);
            throw new DukeParseException(msg);
        }
    }

    /**
     * Parses the time <code>String</code> into a <code>LocalDateTime</code>.
     * The time is converted into a <code>LocalTime</code> first,
     * and then converted into a <code>LocalDateTime</code> with the date of today.
     *
     * @param time the <code>String</code> that is to be parsed.
     * @param pattern the specified format of the time <code>String</code>.
     * @return the parsed <code>LocalDateTime</code>.
     * @throws DukeParseException if the time <code>String</code> does not match
     * the pattern <code>String</code>.
     */
    public static LocalDateTime parseTimeString(
            String time, String pattern) throws DukeParseException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
            LocalTime localTime = LocalTime.parse(time, dtf);
            return LocalDateTime.of(LocalDate.now(), localTime);
        } catch (DateTimeParseException exception) {
            String msg = String.format("Ensure the time passed in is of the form: '%s'.", pattern);
            throw new DukeParseException(msg);
        }
    }
}
