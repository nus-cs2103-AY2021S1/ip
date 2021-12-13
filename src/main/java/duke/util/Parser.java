package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.DukeException;

/**
 * Represents parser for user's input.
 */
public class Parser {
    /**
     * Parse string date to Date date in a neater format.
     * @param strDate Date type in by user.
     * @return Date object in a neater format.
     * @throws DukeException
     */
    public static Date parseDate(String strDate) throws DukeException {
        String formatWithMin = "y-M-d HH:mm";
        String formatWithoutMin = "y-M-d";
        try {
            return new SimpleDateFormat(strDate.length() > 11 ? formatWithMin : formatWithoutMin).parse(strDate);
        } catch (ParseException e) {
            throw new DukeException(MagicStrings.ERROR_TIME_FORMAT_INCORRECT);
        }
    }

    /**
     * Parse command into sections.
     * @param command User's input.
     * @return Array of command sections.
     * @throws DukeException
     */
    public static String[] splitCommand(String command) {
        return command.split(" ", 2);
    }
}
