package duke;

import duke.exceptions.InvalidTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that provides methods to convert time in a certain format.
 */
public class Time {

    /**
     * Converts the given time from string to a LocalDate time object.
     *
     * @param dateString Time in string.
     * @return LocalDateTime of the given time.
     * @throws InvalidTimeException If format of the input time given by the user is wrong.
     */
    public static LocalDateTime getFormattedTime(String dateString) throws InvalidTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Converts the LocalDate time object to a string for easy saving into hardware.
     *
     * @param time Time of the event.
     * @return String format of the time to save.
     */
    public static String convertTimeToSave(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }

    /**
     * Converts the LocalDate time object into a formatted string to display the time
     * in a nicer format.
     *
     * @param time Time of the event.
     * @return String of formatted time.
     */
    public static String toString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return time.format(formatter);
    }
}
