package duke.datetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.util.Pair;

/**
 * Class specifically meant to handle string inputs and convert them into LocalDateTime objects.
 */
public class DateTimeHandler {

    /** String representing standard datetime format used */
    public static final String STANDARD_DATETIME_FORMAT_STRING = "dd-MM-yyyy HHmm";
    /** String representing standard time format */
    public static final String STANDARD_2400_FORMAT_STRING = "HHmm";
    /** DateTimeFormatter object of standard datetime format */
    public static final DateTimeFormatter STANDARD_DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_DATETIME_FORMAT_STRING);
    /** DateTimeFormatter object of standard time format */
    public static final DateTimeFormatter STANDARD_2400_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_2400_FORMAT_STRING);
    /** Length of standard datetime format string */
    private static final int STANDARD_DATETIME_LENGTH = STANDARD_DATETIME_FORMAT_STRING.length();
    /** Length of standard 2400 format string */
    private static final int STANDARD_2400_LENGTH = STANDARD_2400_FORMAT_STRING.length();

    /**
     * Returns a LocalDateTime object from the parsed string.
     * This method handles invalid string formats as well.
     *
     * @param dateTime string to be converted into LocalDateTime object.
     * @return LocalDateTime object from the details given.
     * @throws DukeException if the format of the datetime string is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return formatToLocalDateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException(dateTime + " is an invalid datetime format! Please use "
                    + STANDARD_DATETIME_FORMAT_STRING + " (24hr)");
        }
    }

    /**
     * Returns a pair of LocalDateTime objects from the parsed event timings string.
     * This method handles invalid string formats as well.
     * Besides invalid format, this method checks that the latter timing is later than the earlier timing.
     * Format 1: DD-MM-YYYY HHMM-HHMM
     * Format 2: DD-MM-YYYY HHMM-DD-MM-YYYY HHMM
     *
     * @param eventTiming string to be converted into a pair of LocalDateTime objects.
     * @return pair of LocalDateTime objects from the details given.
     * @throws DukeException if the format of the event-timing string is invalid or end timing later than start.
     */
    public static Pair<LocalDateTime, LocalDateTime> parseEventTimings(String eventTiming) throws DukeException {
        int acceptableLength1 = STANDARD_DATETIME_LENGTH + 1 + STANDARD_2400_LENGTH;
        int acceptableLength2 = STANDARD_DATETIME_LENGTH * 2 + 1;

        boolean isFormat1Length = eventTiming.length() == acceptableLength1;
        boolean isFormat2Length = eventTiming.length() == acceptableLength2;
        if (!isFormat1Length && !isFormat2Length) {
            throw new DukeException(getEventStringFormatError(eventTiming));
        }

        String firstTiming = eventTiming.substring(0, STANDARD_DATETIME_LENGTH);
        String secondTiming = eventTiming.substring(STANDARD_DATETIME_LENGTH + 1);

        try {
            LocalDateTime dateTime1 = formatToLocalDateTime(firstTiming);
            LocalDateTime dateTime2;

            if (secondTiming.length() == STANDARD_DATETIME_LENGTH) {
                dateTime2 = formatToLocalDateTime(secondTiming);;
            } else {
                LocalTime time = formatToLocalTime(secondTiming);
                dateTime2 = LocalDateTime.of(dateTime1.toLocalDate(), time);
            }

            if (dateTime2.compareTo(dateTime1) < 0) {
                throw new DukeException("End timing must be later than start timing!");
            }

            return Pair.of(dateTime1, dateTime2);

        } catch (DateTimeParseException e) {
            throw new DukeException(getEventStringFormatError(eventTiming));

        }
    }


    /** Returns a formatted error message for the parseEventTimings method */
    private static String getEventStringFormatError(String eventTiming) {
        return eventTiming + " is not a valid event timing. Please use either\n"
                + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_DATETIME_FORMAT_STRING + " (24hr)\n"
                + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_2400_FORMAT_STRING + " (24hr)";
    }

    /** Returns a LocalDateTime object from the string provided */
    private static LocalDateTime formatToLocalDateTime(String datetimeString) throws DateTimeParseException {
        return LocalDateTime.parse(datetimeString, STANDARD_DATETIME_FORMAT);
    }

    /** Returns a LocalTime object from the string provided */
    private static LocalTime formatToLocalTime(String timeString) throws DateTimeParseException {
        return LocalTime.parse(timeString, STANDARD_2400_FORMAT);
    }




}
