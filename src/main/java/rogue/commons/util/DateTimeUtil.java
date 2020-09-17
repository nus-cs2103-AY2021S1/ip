package rogue.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class to manage dates and times.
 */
public class DateTimeUtil {
    /**
     * Parses a date string of format "dd-MM-yyyy" into a {@code LocalDate} object.
     *
     * @param dateString The date string.
     * @return {@LocalDate} matching the date provided
     * @throws DateTimeParseException when the given date string does not conform to the format.
     */
    public static LocalDate parseStringToDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return LocalDate.parse(dateString, formatter);
    }
}
