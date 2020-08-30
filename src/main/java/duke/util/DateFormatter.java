package duke.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import duke.exception.DukeException;

/**
 * Contains utility methods for converting between Dates and Strings.
 */
public class DateFormatter {
    // @@author aizatazhar-reused
    // Reused from https://stackoverflow.com/a/16990333/12003017 with minor modifications
    private static List<String> dateFormats = Arrays.asList(
            "dd-MM-yyyy hhmm",
            "dd-MM-yyyy"
    );

    /**
     * Parses a String representation of a date and returns a Date object.
     *
     * @param strDate String representation of a date.
     * @return Date object corresponding to the String given.
     * @throws DukeException If the String representation does not match any defined date format.
     */
    public static Date extractTimestampInput(String strDate) throws DukeException {
        for (String format: dateFormats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(strDate);
            } catch (ParseException e) {
                // intentionally empty to check the other date formats
            }
        }

        throw new DukeException("Invalid input for date. Given '" + strDate + "', "
                + "expecting format dd-MM-yyyy hhmm");
    }
    // @@author

    /**
     * Formats a date for displaying on the UI.
     *
     * @param date Date object to format.
     * @return String representation of the date in a display-friendly format.
     */
    public static String formatDisplay(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return dateFormat.format(date);
    }

    /**
     * Formats a date for saving.
     *
     * @param date Date object to format.
     * @return String representation of the date in a save-friendly format.
     */
    public static String formatSave(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hhmm");
        return dateFormat.format(date);
    }
}
