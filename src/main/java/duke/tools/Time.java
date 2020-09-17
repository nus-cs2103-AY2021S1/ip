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
    //Idea of natural language enlightened by https://jinhao-l.github.io/ip/.
    //Corresponding element position to dateBuilder is crucial
    private final String[] naturalDate = new String[] {
        "today", "tomorrow"
    };

    //Corresponding element position to naturalDate is crucial
    private final String[] dateBuilder = new String[] {
        LocalDateTime.now().getDayOfMonth() + "",
        LocalDateTime.now().getDayOfMonth() + 1 + ""
    };

    //Corresponding element position to timeBuilder is crucial
    private final String[] naturalTime = new String[] {
        "night", "morning", "noon", "midnight", "afternoon"
    };

    //Corresponding element position to naturalTime is crucial
    private final String[] timeBuilder = new String[] {
        "_2000", "_0800", "_1200", "_2359", "_1400"
    };

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
     * Constructs a <code>Time</code>.
     *
     * @param time a string with time format.
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
        String[] timeArray = time.split(" ");
        StringBuilder dateAndTime = new StringBuilder();

        //check if the first element matches to any elements in naturalDate.
        for (int i = 0; i < naturalDate.length; i++) {
            if (timeArray[0].equals(naturalDate[i])) {
                dateAndTime.append(dateBuilder[i]);
            }
        }

        //check if the second element matches to any elements in naturalTime.
        for (int i = 0; i < naturalTime.length && timeArray.length == 2; i++) {
            if (timeArray[1].equals(naturalTime[i])) {
                dateAndTime.append(timeBuilder[i]);
            }
        }

        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatterList) {
            try {
                if (dateAndTime.toString().equals("")) {
                    return LocalDateTime.parse(time, dateTimeFormatter);
                } else {
                    return LocalDateTime.parse(dateAndTime.toString(), dateTimeFormatter);
                }
            } catch (DateTimeParseException e) {
                //The loop needs to continue until find the correct time format.
            }
        }

        //recurse on the result.
        timeParser(dateAndTime.toString());

        throw new DukeException(Exceptions.TIMEFORMATEXCEPTION);
    }

    @Override
    public String toString() {
        return localDateTime.format(DateTimeFormatter.ofPattern("EEEE_MMM_d_yyyy_HH:mm"));
    }
}
