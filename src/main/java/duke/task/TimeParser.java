package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

public class TimeParser {
    /** Returns the date in format "MMM d yyyy".
     *
     * @param str String of date in form yyyy-mm-dd.
     * @return String of date in form "MMM d yyyy".
     */
    public static String parseTime(String str) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(str);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Time cannot been processed. Give the time strictly in yyyy-mm-dd format.");
        }
    }

    /**
     * Checks if the date string is in valid form.
     *
     * @param str String of date.
     * @return Boolean indicating whether date string is valid.
     */
    public static boolean isValidTime(String str) {
        String[] arr = str.split("-", 3);
        if (arr.length == 3) {
            int year = Integer.parseInt(arr[0]);
            int month = Integer.parseInt(arr[1]);
            int date = Integer.parseInt(arr[2]);
            return month <= 12 && date <= 31;
        } else {
            return false;
        }
    }
}

