package duke.parser;

import static duke.util.Keyword.DATE_INPUT_FORMAT;
import static duke.util.Keyword.DATE_TIME_INPUT_FORMAT;
import static duke.util.Keyword.TIME_INPUT_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/**
 * Parses the user date or time format into a {@code LocalDateTime} object.
 */
public abstract class DateTimeParser {

    /**
     * Parses the input string into a {@code LocalDateTime} object with d MMM yyyy @ h.mm a format.
     *
     * @param deadline Deadline of task before parsing.
     * @return A LocalDateTime object.
     * @throws InvalidDateException If the deadline of task does not follow the proper format.
     */
    public static LocalDateTime getDateTime(String deadline) throws InvalidDateException {
        if (isDateTimeFormat(deadline)) {
            return formatDateTime(deadline);
        } else if (isDateFormat(deadline)) {
            return formatDate(deadline);
        } else if (isTimeFormat(deadline)) {
            return formatTime(deadline);
        } else {
            throw new InvalidDateException();
        }
    }

    /**
     * Checks if the input given matches the d-M-yy HHmm format.
     *
     * @param input User input.
     * @return True if input has the right format, false otherwise.
     */
    private static boolean isDateTimeFormat(String input) {
        try {
            LocalDateTime.parse(input, formatterDateTime(DATE_TIME_INPUT_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a LocalDateTime object with d MMM yyyy @ h.mm a format.
     *
     * @param dateAndTime Input date and time from user.
     * @return Formatted date and time.
     */
    private static LocalDateTime formatDateTime(String dateAndTime) {
        return LocalDateTime.parse(dateAndTime, formatterDateTime(DATE_TIME_INPUT_FORMAT));
    }

    /**
     * Checks if the input given matches the d-M-yy format.
     *
     * @param input User input.
     * @return True if input has the right format, false otherwise.
     */
    private static boolean isDateFormat(String input) {
        try {
            LocalDate.parse(input, formatterDateTime(DATE_INPUT_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a LocalDateTime object with d MMM yyyy @ h.mm a format.
     *
     * @param date Input date from user.
     * @return A LocalDate object.
     */
    private static LocalDateTime formatDate(String date) {
        LocalDate localDate = LocalDate.parse(date, formatterDateTime(DATE_INPUT_FORMAT));
        LocalTime currentTime = LocalTime.now();
        return LocalDateTime.of(localDate, currentTime);
    }

    /**
     * Checks if the input given is in a HHmm format.
     *
     * @param input User input.
     * @return True if input has the right format, false otherwise.
     */
    private static boolean isTimeFormat(String input) {
        try {
            LocalTime.parse(input, formatterDateTime(TIME_INPUT_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a LocalDateTime object with d MMM yyyy @ h.mm a format.
     *
     * @param time Input time from user.
     * @return LocalDateTime consisting of today's date input time.
     */
    private static LocalDateTime formatTime(String time) {
        LocalTime localTime = LocalTime.parse(time, formatterDateTime(TIME_INPUT_FORMAT));
        LocalDate currentDate = LocalDate.now();
        return LocalDateTime.of(currentDate, localTime);
    }

    /**
     * Creates a DateTimeFormatter using the input pattern.
     *
     * @param pattern String pattern.
     * @return The DateTimeFormatter based the pattern.
     */
    private static DateTimeFormatter formatterDateTime(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
