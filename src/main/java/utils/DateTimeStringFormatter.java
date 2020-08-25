package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting {@code LocalDateTime} objects as strings.
 */
public class DateTimeStringFormatter {
    /** Format for displaying datetime */
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Formats the {@code LocalDateTime} as a human-readable string.
     *
     * @param localDateTime a {@code LocalDateTime} object describing the datetime.
     * @return a formatted string representation of the datetime.
     */
    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(displayFormatter);
    }
}
