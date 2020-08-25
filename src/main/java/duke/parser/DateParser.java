package duke.parser;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateParser {

    private static final List<String> ACCEPTED_FORMATS_WITH_TIME = Arrays.asList("d MMM uuuu HH:mm", "d-M-uuuu HH:mm", "d/M/uuuu HH:mm");

    private static final List<String> ACCEPTED_FORMATS_DATE_ONLY = Arrays.asList("d MMM uuuu", "d-M-uuuu", "d/M/uuuu");

    private static final int HAS_TIME_INDICATOR = 0;
    private static final int NULL_TIME_INDICATOR = 30;

    public static LocalDateTime parseString(String input) throws DukeException {
        if (input.contains(":")) {
            for (String format : ACCEPTED_FORMATS_WITH_TIME) {
                try {
                    return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format)).withSecond(HAS_TIME_INDICATOR);
                } catch (DateTimeParseException e) {
                }
            }
        } else {
            for (String format : ACCEPTED_FORMATS_DATE_ONLY) {
                try {
                    // Since we do not support seconds for date and time based information, we use the second field to
                    // differentiate between a LocalDateTime with no defined time and one with time defined at midnight.
                    return LocalDate.parse(input, DateTimeFormatter.ofPattern(format)).atStartOfDay().withSecond(NULL_TIME_INDICATOR);
                } catch (DateTimeParseException e) {
                }
            }
        }
        throw new DukeException("I'm not quite sure if we know each other...");
    }

    public static String parseLocalDateTime(LocalDateTime dateTime) {
        if (dateTime.getSecond() == 30) {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_DATE_ONLY.get(0)));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern(ACCEPTED_FORMATS_WITH_TIME.get(0)));
        }
    }
}
