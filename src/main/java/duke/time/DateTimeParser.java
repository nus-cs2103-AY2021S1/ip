package duke.time;

import duke.exception.DukeInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains a static method for handling parsing string inputs to <code>DateTime</code> objects.
 */
public class DateTimeParser {

    /**
     * Parses string input, that is a representation of a point in time, into a <code>DateTime</code>.
     * Input must be format compatible.
     * For the string to be compatible, either "d/M/y" or "d/M/y H:m" formats must be provided.
     *
     * @param text Input to be parsed.
     * @return <code>DateTime</code> representation of date and time in <code>text</code>.
     * @throws DukeInputException If input string is poorly formatted.
     */
    public static DateTime parse(String text) throws DukeInputException {

        String[] dateTimeSplit = text.split(" ", 2);

        String errorMessage = "Entry not duke.time.DateTime compatible\n"
                + "Use <dd/MM/yyyy hh:mm> such as 12/5/2002 13:14";

        // Attempt to create a Date object
        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateTimeSplit[0], DateTimeFormatter.ofPattern("d/M/y"));
        } catch (DateTimeParseException e) {
            throw new DukeInputException(errorMessage);
        }

        //adjust date to within +- 50 years
        if (date.getYear() < 100) {
            date = date.plusYears(2000);

        }

        if (date.isAfter(LocalDate.now().plusYears(50))) {
            date = date.minusYears(100);
        }

        // If time data available, add time data to resultant DateTime
        if (dateTimeSplit.length == 2) {
            try {
                time = LocalTime.parse(dateTimeSplit[1], DateTimeFormatter.ofPattern("H:m"));
                return new DateTime(date, time);
            } catch (DateTimeParseException e) {
                //if unable to parse as time, take it that there is no time entry.
            }
        }

        return new DateTime(date);
    }

}
