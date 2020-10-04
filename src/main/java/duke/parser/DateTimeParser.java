package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.util.DateTime;

/**
 * Class that parses datetime input from the user and returns a {@link DateTime}.
 */
public class DateTimeParser {

    private static final String[] ACCEPTED_DATE_FORMATS = {"yyyy/M/d", "d/M/yyyy", "yyyy/MMM/d", "d/MMM/yyyy",
        "yyyy-M-d", "d-M-yyyy", "yyyy-MMM-d", "d-MMM-yyyy"};
    private static final String[] ACCEPTED_TIME_FORMATS = {"HH[:]mm", "h:mma", "h.mma", "ha"};

    /**
     * Parses datetime input from the user.
     *
     * @param input Datetime input from the user.
     * @return Parsed {@link DateTime}.
     * @throws DukeException If the input datetime is of an invalid format.
     */
    public static DateTime parse(String input) throws DukeException {
        String[] dateTimeArgs = input.split(" ", 2);
        assert dateTimeArgs.length > 0 : "Datetime input cannot be empty";

        LocalDate date = parseDate(dateTimeArgs[0]);

        if (dateTimeArgs.length == 1) {
            return new DateTime(input, date);
        }

        LocalTime time = parseTime(dateTimeArgs[1]);
        return new DateTime(input, date, time);
    }

    private static LocalDate parseDate(String dateInput) throws DukeException {
        for (String format : ACCEPTED_DATE_FORMATS) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
                return LocalDate.parse(dateInput, dateFormatter);
            } catch (DateTimeParseException e) {
                //Test other formats
            }
        }
        //If no matching format can be found
        throw new DukeException("Invalid date or time format provided!");
    }

    private static LocalTime parseTime(String timeInput) throws DukeException {
        for (String format : ACCEPTED_TIME_FORMATS) {
            try {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
                return LocalTime.parse(timeInput, timeFormatter);
            } catch (DateTimeParseException e) {
                //Test other formats
            }
        }
        //If no matching format can be found
        throw new DukeException("Invalid date or time format provided!");
    }
}
