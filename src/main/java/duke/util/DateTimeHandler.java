package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeHandler {
    /**
     * Parses the date and time string, and returns a LocalDateTime object if the date and time string is in accordance
     * to any of the listed formats.
     * If date and time string does not follow any of the listed formats, null is returned.
     *
     * @param dateString String representing the date and time.
     * @return LocalDateTime object if parsing the successful.
     */
    public static LocalDateTime tryParseDateTime(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd HHmm", "yyyy-MM-d HHmm", "dd/MM/yyyy HHmm",
                "dd/M/yyyy HHmm", "d/MM/yyyy HHmm", "d/M/yyyy HHmm", "dd-MM-yyyy HHmm", "dd-M-yyyy HHmm",
                "d-MM-yyyy HHmm", "d-M-yyyy HHmm");

        for (String formatString : formatStrings) {
            try {
                return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                //dateString will not be interpreted to contain a Date and Time
            }
        }
        return null;
    }

    /**
     * Parses the date string, and returns a LocalDate object if the date string is in accordance
     * to any of the listed formats.
     * If date string does not follow any of the listed formats, null is returned.
     *
     * @param dateString String representing the date.
     * @return LocalDate object if parsing the successful.
     */
    public static LocalDate tryParseDate(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-d", "dd/MM/yyyy", "d/MM/yyyy",
                "dd/M/yyyy", "d/M/yyyy", "dd-MM-yyyy", "dd-M-yyyy", "d-MM-yyyy", "d-M-yyyy");

        for (String formatString : formatStrings) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(formatString));
            } catch (DateTimeParseException e) {
                //dateString will not be interpreted to contain a Date
            }
        }
        return null;
    }

    /**
     * Checks if date time information is parsed as LocalDateTime.
     *
     * @param dateTime LocalDateTime object.
     * @return If date time information is parsed as LocalDateTime.
     */
    public static boolean isDateTimeParsed(LocalDateTime dateTime) {
        return dateTime != null;
    }

    /**
     * Generates a string for printing from either the LocalDateTime object, LocalDate object, or date time String.
     *
     * @param dateTimeString String representing date and/or time information.
     * @param date           LocalDate representing date and/or time information.
     * @param dateTime       LocalDateTime representing date and/or time information.
     * @return String representing the date time information for printing.
     */
    public static String generateDateTimeFormat(String dateTimeString, LocalDate date, LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else {
            return dateTimeString;
        }
    }

}
