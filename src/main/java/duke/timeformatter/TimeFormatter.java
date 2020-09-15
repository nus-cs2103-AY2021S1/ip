package duke.timeformatter;

import java.time.LocalDate;

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
    public static LocalDate localDate(String Date) {
        String date = Date.trim().replaceAll("/", "-");
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    /**
     * @param localDate LocalDate to be converted to String representation.
     * @return String representation of date.
     */
    public static String prettyDate(LocalDate localDate) {
        return localDate.getDayOfWeek() + "," + localDate.getMonth().name().substring(0, 3)
                + " " + localDate.getDayOfMonth() + " " + localDate.getYear();
    }
}
