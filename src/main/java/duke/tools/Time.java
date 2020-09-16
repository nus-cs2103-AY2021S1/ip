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
 * Converts the time string to <code>LocalDateTime</code>.
 */
public class Time {
    private final String[] timeFormatString = new String[]{
        "yyyy-M-d[_HHmm]",
        "yyyy/M/d[_HHmm]",
        "M-d[_HHmm]",
        "M/d[_HHmm]",
        "d[_HHmm]",
        "HHmm"
    };
    private final List<DateTimeFormatter> dateTimeFormatterList = createTimeFormat();
    private LocalDateTime localDateTime;

    /**
     * Constructs a Time.
     *
     * @param time a string of format
     *             of "yyyy-mm-dd".
     */
    public Time(String time) throws DukeException {
        localDateTime = timeParser(time);
    }

    //Solution below adapted from
    //https://www.codota.com/code/java/methods/java.time.format.DateTimeFormatterBuilder/appendPattern
    private List<DateTimeFormatter> createTimeFormat() {
        List<DateTimeFormatter> result = new ArrayList<>();
        for (String s : timeFormatString) {
            result.add(
                    new DateTimeFormatterBuilder()
                            .appendPattern(s)
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

    @Override
    public String toString() {
        return localDateTime.format(DateTimeFormatter.ofPattern("EEEE_MMM_d_yyyy_HH:mm"));
    }
}
