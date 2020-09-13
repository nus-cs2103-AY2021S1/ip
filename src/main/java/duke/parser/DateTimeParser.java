package duke.parser;

import static duke.util.Keyword.SINGLE_SPACE;
import static duke.util.Keyword.TIME_SYMBOL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/**
 * Parses the deadline of the {@code Deadline} task into a MMM d yyyy / h.mm a format.
 */
public abstract class DateTimeParser {

    /**
     * Parses the input string into a MMM d yyyy / h.mm a format.
     * @param deadline Deadline of task before parsing.
     * @return A MMM d yyyy / h.mm a format of the deadline.
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
     * Checks if the input given is in a LocalDateTime format, e.g. yyyy-MM-dd hh:mm.
     *
     * @param input User input.
     * @return True if input is in a LocalDateTime format, false otherwise.
     */
    private static boolean isDateTimeFormat(String input) {
        try {
            LocalDateTime.parse(matchDateTimeFormat(input));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Formats the date and time to a MMM d yyyy / h.mm a format.
     *
     * @param dateAndTime Input date and time from user.
     * @return Formatted date and time.
     */
    private static LocalDateTime formatDateTime(String dateAndTime) {
        return LocalDateTime.parse(matchDateTimeFormat(dateAndTime));
    }

    /**
     * Matches the user input to the date and time format.
     *
     * @param input Input String.
     * @return Formatted String.
     */
    private static String matchDateTimeFormat(String input) {
        return input.replace(SINGLE_SPACE, TIME_SYMBOL);
    }

    /**
     * Checks if the input given is in a LocalDate format, e.g. yyyy-MM-dd.
     *
     * @param input User input.
     * @return True if input is in a LocalDate format, false otherwise.
     */
    private static boolean isDateFormat(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Formats the date to a MMM d yyyy / h.mm a format.
     *
     * @param date Input date from user.
     * @return A LocalDate object.
     */
    private static LocalDateTime formatDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime currentTime = LocalTime.now();
        return LocalDateTime.of(localDate, currentTime);
    }

    /**
     * Checks if the input given is in a LocalTime format, e.g. hh:mm.
     *
     * @param input User input.
     * @return True if input is in a LocalTime format, false otherwise.
     */
    private static boolean isTimeFormat(String input) {
        try {
            LocalTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Formats the time to a MMM d yyyy / h.mm a format.
     *
     * @param time Input time from user.
     * @return LocalDateTime consisting of today's date input time.
     */
    private static LocalDateTime formatTime(String time) {
        LocalTime localTime = LocalTime.parse(time);
        LocalDate currentDate = LocalDate.now();
        return LocalDateTime.of(currentDate, localTime);
    }
}
