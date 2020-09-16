package duke.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.Exceptions;


/**
 * Converts the time string
 * of format "yyyy-MM-dd" into "MMM_d_yyyy".
 */
public class Time {
    private final String[] timeFormatString = new String[]{
        "yyyy-M-d[_HHmm]",
        "yyyy/M/d[_HHmm]",
        "M-d[_HHmm]",
        "M/d[_HHmm]",
        "d[_HHmm]",
        "HHmm"

//        "d/M[/uuuu][ HHmm]", "d-M[-uuuu][ HHmm]",
//        "M/d[/uuuu][ HHmm]", "M-d[-uuuu][ HHmm]",
//        "uuuu/M/d[ HHmm]", "uuuu-M-d[ HHmm]",
//        "d-MMM[-uuuu][ HHmm]", "d MMM[ HHmm]"
    };
    private final List<DateTimeFormatter> dateTimeFormatterList = createTimeFormat();
    private LocalDateTime localDateTime;

    /**
     * Constructs a Time.
     *
     * @param time A string of format
     *             of "yyyy-mm-dd".
     */
    public Time(String time) throws DukeException {
        localDateTime = timeParser(time);
    }

    //Solution below adapted from
    //https://www.codota.com/code/java/methods/java.time.format.DateTimeFormatterBuilder/appendPattern
    private List<DateTimeFormatter> createTimeFormat() {
        List<DateTimeFormatter> result = new ArrayList<>();
        for (int i = 0; i < timeFormatString.length; i++) {
            result.add(
                    new DateTimeFormatterBuilder()
                    .appendPattern(timeFormatString[i])
                    .parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue())
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth())
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter()
            );
        }
        return result;
    }

    private LocalDateTime timeParser(String time) throws DukeException {
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatterList) {
            try {
                return LocalDateTime.parse(time, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                //The loop needs to continue until find the correct time format.
            }
        }
        // Need to change to command
        throw new DukeException(Exceptions.TIMEFORMATEXCEPTION);
    }

//    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidCommandException {
//        for (int i = 0; i < KNOWN_DT_FORMATS.size(); i++) {
//            try {
//                return LocalDateTime.parse(dateTimeString, KNOWN_DT_FORMATS.get(i));
//            } catch (DateTimeParseException ex) {
//                // ignore
//            }
//        }
//
//        throw new InvalidCommandException("Invalid datetime! Please use 24h format for time");
//    }

    @Override
    public String toString() {
        return localDateTime.format(DateTimeFormatter.ofPattern("EEEE_MMM_d_yyyy_HH:mm"));
    }
}
