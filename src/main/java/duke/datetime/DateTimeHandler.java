package duke.datetime;

import duke.DukeException;
import duke.util.Pair;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class specifically meant to handle string inputs and convert them into LocalDateTime objects.
 */
public class DateTimeHandler {

    /** String representing standard datetime format used */
    public static final String STANDARD_DATETIME_FORMAT_STRING = "dd-MM-uuuu HHmm";
    /** String representing standard time format */
    public static final String STANDARD_2400_FORMAT_STRING = "HHmm";
    /** DateTimeFormatter object of standard datetime format */
    public static final DateTimeFormatter STANDARD_DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_DATETIME_FORMAT_STRING);
    /** DateTimeFormatter object of standard time format */
    public static final DateTimeFormatter STANDARD_2400_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_2400_FORMAT_STRING);

    /**
     * Parses a string be converted into a single LocalDateTime object.
     * @param dateTime string to be converted into LocalDateTime object
     * @return LocalDateTime object from the details given
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, STANDARD_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(dateTime + " is an invalid datetime format! Please use "
                    + STANDARD_DATETIME_FORMAT_STRING + " (24hr)");
        }
    }

    /**
     * Parses a string be converted into a pair of start and end timings.
     * Besides invalid format, this method checks that the latter timing is later than the earlier timing.
     * @param eventTiming string to be converted into a pair of LocalDateTime objects
     * @return pair of LocalDateTime objects from the details given
     */
    public static Pair<LocalDateTime, LocalDateTime> parseEventTimings(String eventTiming) {
        int acceptableLength1 = STANDARD_DATETIME_FORMAT_STRING.length() + 1 + STANDARD_2400_FORMAT_STRING.length();
        int acceptableLength2 = STANDARD_DATETIME_FORMAT_STRING.length() * 2 + 1;
        if (eventTiming.length() != acceptableLength1 && eventTiming.length() != acceptableLength2) {
            throw new DukeException(eventTiming + " is not a valid event timing. Please use either\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_DATETIME_FORMAT_STRING + " (24hr)\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_2400_FORMAT_STRING + " (24hr)");
        }
        String firstTiming = eventTiming.substring(0, STANDARD_DATETIME_FORMAT_STRING.length());
        String secondTiming = eventTiming.substring(STANDARD_DATETIME_FORMAT_STRING.length() + 1);
        try {
            LocalDateTime dateTime1 = LocalDateTime.parse(firstTiming, STANDARD_DATETIME_FORMAT);
            LocalDateTime dateTime2;
            if (secondTiming.length() == STANDARD_DATETIME_FORMAT_STRING.length()) {
                dateTime2 = LocalDateTime.parse(secondTiming, STANDARD_DATETIME_FORMAT);
            } else {
                LocalTime time = LocalTime.parse(secondTiming, STANDARD_2400_FORMAT);
                dateTime2 = LocalDateTime.of(dateTime1.toLocalDate(), time);
            }

            if (dateTime2.compareTo(dateTime1) < 0) {
                throw new DukeException("End timing must be later than start timing!");
            }
            return Pair.of(dateTime1, dateTime2);

        } catch (DateTimeParseException e) {
            throw new DukeException(eventTiming + " is not a valid event timing. Please use either\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_DATETIME_FORMAT_STRING + " (24hr)\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_2400_FORMAT_STRING + " (24hr)");

        }
    }



}
