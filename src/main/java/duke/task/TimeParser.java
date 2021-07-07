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

}

