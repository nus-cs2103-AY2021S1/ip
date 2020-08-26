package duke.timeformatter;

import java.time.LocalDate;

/**
 * Convert the user's input for time to be parse by LocalDate.
 */
public class TimeFormatter {

    /**
     * Convert string input to LocalDate
     *
     * @param by user's string input.
     * @return LocalDate corresponding to the string input.
     */
    public static LocalDate localDate(String by) {
        String date = by.trim().replaceAll("/", "-");
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    /**
     * @param localDate
     * @return string in day of week, month, day and year format.
     */
    public static String prettyDate(LocalDate localDate) {
        return localDate.getDayOfWeek() + "," + localDate.getMonth().name().substring(0, 3)
                + " " + localDate.getDayOfMonth() + " " + localDate.getYear();
    }
}
