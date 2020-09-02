package duke.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
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
            LocalDateTime dateTime = LocalDateTime.parse(timeString, TimeFormat.dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException exception1) {
            try {
                LocalDate date = LocalDate.parse(timeString, TimeFormat.dateFormatter);
                return date.atTime(LocalTime.MIN);
            } catch (DateTimeParseException exception2) {
                try {
                    LocalTime time = LocalTime.parse(timeString, TimeFormat.timeFormatter1);
                    return LocalDate.now().atTime(time);
                } catch (DateTimeParseException exception3) {
                    try {
                        LocalTime time = LocalTime.parse(timeString, TimeFormat.timeFormatter2);
                        return LocalDate.now().atTime(time);
                    } catch (DateTimeParseException exception4) {
                        return null;
                    }
                }
            }
        }
    }
}
