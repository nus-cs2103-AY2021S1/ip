package duke.util;

import duke.exception.DukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Contains utility methods for converting between Dates and Strings.
 */
public class DateFormatter {
    // @@author aizatazhar-reused
    // Reused from https://stackoverflow.com/a/16990333/12003017 with minor modifications
    private static List<String> dateFormats = Arrays.asList(
            "yyyy-MM-dd hhmm",
            "yyyy-MM-dd"
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
                //intentionally empty
            }
        }

        throw new DukeException("Invalid input for date. Given '" + strDate + "', "
                + "expecting format yyyy-MM-dd hhmm or yyyy-MM-dd.");
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
