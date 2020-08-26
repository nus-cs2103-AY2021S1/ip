package duke.parser;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code DateParser} class provides methods for converting between {@code LocalDateTime} objects and strings.
 */
public class DateParser {

    private static final List<String> ACCEPTED_FORMATS_WITH_TIME = Arrays.asList("d MMM uuuu HH:mm", "d-M-uuuu HH:mm",
            "d/M/uuuu HH:mm");

    private static final List<String> ACCEPTED_FORMATS_DATE_ONLY = Arrays.asList("d MMM uuuu", "d-M-uuuu", "d/M/uuuu");

    private static final int HAS_TIME_INDICATOR = 0;
    private static final int NULL_TIME_INDICATOR = 30;

    /**
     * Converts the specified input into a {@code LocalDateTime} object.
     * Does not support storing seconds.
     *
     * @param input the string to be converted.
     * @return the {@code LocalDateTime} object representing the specified date and time.
     * @throws DukeException if the format of the input is not recognised.
     */
    public static LocalDateTime parseString(String input) throws DukeException {
        if (input.contains(":")) {
            for (String format : ACCEPTED_FORMATS_WITH_TIME) {
                try {
                    return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format))
                            .withSecond(HAS_TIME_INDICATOR);
                } catch (DateTimeParseException e) {
                }
            }
        } else {
            for (String format : ACCEPTED_FORMATS_DATE_ONLY) {
                try {
                    // Since we do not support seconds for date and time based information, we use the second field to
                    // differentiate between a LocalDateTime with no defined time and one with time defined at midnight.
                    return LocalDate.parse(input, DateTimeFormatter.ofPattern(format)).atStartOfDay()
                            .withSecond(NULL_TIME_INDICATOR);
                } catch (DateTimeParseException e) {
                }
            }
        }
        throw new DukeException("I'm not quite sure if we know each other...");
    }

    /**
     * Converts the specified {@code LocalDateTime} object to a string.
     *
     * @param dateTime the {@code LocalDateTime} object to be converted.
     * @return the string representation of the specified {@code LocalDateTime} object.
     */
    public static String parseLocalDateTime(LocalDateTime dateTime) {
        if (dateTime.getSecond() == 30) {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_DATE_ONLY.get(0)));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_WITH_TIME.get(0)));
        }
    }
}
