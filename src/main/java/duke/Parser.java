package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Parser is responsible to do all the parsing required
 * for duke chatbot.
 */
public class Parser {

    /**
     * Splits a sentence into command and its description.
     *
     * @param fullCommand sentence given by user.
     * @return String[] string array with command in index 0 and description in index 1.
     */
    public static String[] splitCommandAndDescription(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    /**
     * Splits the deadline description and time.
     *
     * @param fullCommand sentence containing both description and time.
     * @return String[] string array with description in index 0 and time in index 1.
     */
    public static String[] splitDeadlineTime(String fullCommand) {
        return fullCommand.split("/by ", 2);
    }

    /**
     * Splits the event description and time.
     *
     * @param fullCommand sentence containing both description and time.
     * @return String[] string array with description in index 0 and time in index 1.
     */
    public static String[] splitEventTime(String fullCommand) {
        return fullCommand.split("/at ", 2);
    }

    /**
     * Parses from string to date format.
     *
     * @param string string of day.
     * @return LocalDate date format.
     */
    public static LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate myDateObj = LocalDate.parse(string, formatter);
        return myDateObj;
    }

    /**
     * Parses from date format to string.
     *
     * @param date date.
     * @return string string of the date.
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
