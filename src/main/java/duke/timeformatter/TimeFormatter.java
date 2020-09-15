package duke.timeformatter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateFormatException;

/**
 * Convert the user's input for date to be parse by LocalDate.
 */
public class TimeFormatter {

    /**
     * Convert string input to LocalDate
     *
     * @param Date User's date string input.
     * @return LocalDate corresponding to the string input.
     */
    public static LocalDate localDate(String Date) throws DukeException {
        try {
            String date = Date.trim().replaceAll("/", "-");
            LocalDate localDate = LocalDate.parse(date);
            return localDate;
        } catch (DateTimeParseException ex) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * @param localDate LocalDate to be converted to String representation.
     * @return String representation of date.
     */
    public static String prettyDate(LocalDate localDate) {
        return localDate.getDayOfWeek() + ", " + localDate.getMonth().name().substring(0, 3)
                + " " + localDate.getDayOfMonth() + " " + localDate.getYear();
    }
}
