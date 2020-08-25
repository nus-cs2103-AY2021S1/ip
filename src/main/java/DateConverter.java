import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class handles all formatting for dates in Deadline and Event objects
 */
public class DateConverter {

    private static final List<String> ACCEPTED_FORMATS_WITH_TIME = Arrays.asList("d MMM yyyy HH:mm", "d-M-yyyy HH:mm", "d/M/yyyy HHmm", "d/M/yyyy HH:mm");

    private static final List<String> ACCEPTED_FORMATS_DATE_ONLY = Arrays.asList("d MMM yyyy", "d-M-yyyy", "d/M/yyyy");
    private static final int HAS_TIME_INDICATOR = 0;
    private static final int NULL_TIME_INDICATOR = 30;

    /**
     * compares accepted date formats with the string input. If it matches, it will create the Date in the fomrat
     * @param input String containing date
     * @return LoacalDateTime object
     */
    public static LocalDateTime parseString(String input) {
        try {
            for (String format : ACCEPTED_FORMATS_WITH_TIME) {
                try {
                    return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format)).withSecond(HAS_TIME_INDICATOR);
                } catch (DateTimeParseException ignored) {
                }
            }

            for (String format : ACCEPTED_FORMATS_DATE_ONLY) {
                try {
                    // Since we do not support seconds for date and time based information, we use the second field to
                    // differentiate between a LocalDateTime with no defined time and one with time defined at midnight.
                    return LocalDate.parse(input, DateTimeFormatter.ofPattern(format)).atStartOfDay().withSecond(NULL_TIME_INDICATOR);
                } catch (DateTimeParseException ignored) {
                }
            }
        } catch (Exception e) {
            throw new DukeException("Unable to find time");
        }
        return LocalDateTime.parse(input);
    }

    /**
     * takes a LocalDateTime Object and converts it back into String
     * @param dateTime LocalDateTime object
     * @return String form of dateTime
     */
    public static String parseLocalDateTime(LocalDateTime dateTime) {
        if (dateTime.getSecond() == 30) {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_DATE_ONLY.get(0)));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_WITH_TIME.get(0)));
        }
    }
}

