package duke.utils;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for converting strings into {@code LocalDateTime} objects.
 */
public class DateTimeParser {
    /** Format for date and time. */
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /** Format for date only. */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Parses a string into a {@code LocalDateTime} object if possible.
     *
     * @param dateTimeString the string to be passed.
     * @return a {@code LocalDateTime} object describing the parsed datetime.
     * @throws DukeException if the string is of an invalid datetime format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                localDateTime = LocalDate.parse(dateTimeString, dateFormatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new DukeException(ResourceHandler.getString("exception.invalidDateTime"));
            }
        }
        return localDateTime;
    }
}
