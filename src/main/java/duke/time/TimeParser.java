package duke.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * TimeParser tries to parse a string to a specific time.
 */
public class TimeParser {

    /**
     * Tries to parse a string to LocalDateTime.
     * @param timeString the string to be parsed
     * @return the result LocalDateTime, null when cannot parse.
     */
    public static LocalDateTime parse(String timeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timeString, TimeFormat.DATE_TIME_FORMATTER);
            return dateTime;
        } catch (DateTimeParseException exception1) {
            try {
                LocalDate date = LocalDate.parse(timeString, TimeFormat.DATE_FORMATTER);
                return date.atTime(LocalTime.MIN);
            } catch (DateTimeParseException exception2) {
                try {
                    LocalTime time = LocalTime.parse(timeString, TimeFormat.TIME_FORMATTER_1);
                    return LocalDate.now().atTime(time);
                } catch (DateTimeParseException exception3) {
                    try {
                        LocalTime time = LocalTime.parse(timeString, TimeFormat.TIME_FORMATTER_2);
                        return LocalDate.now().atTime(time);
                    } catch (DateTimeParseException exception4) {
                        return null;
                    }
                }
            }
        }
    }
}
