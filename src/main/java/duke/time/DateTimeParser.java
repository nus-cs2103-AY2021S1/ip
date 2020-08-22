package duke.time;

import duke.exception.DukeInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public static DateTime parse(String text) throws DukeInputException {

        String[] dateTimeSplit = text.split(" ", 2);

        String errorMessage = "Entry not duke.time.DateTime compatible\n"
                + "Use <dd/MM/yyyy hh:mm> such as 12/5/2002 13:14";

        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateTimeSplit[0], DateTimeFormatter.ofPattern("d/M/y"));
        } catch (DateTimeParseException e) {
            throw new DukeInputException(errorMessage);
        }

        //adjust date to 1970-2070
        if (date.getYear() < 100) {
            date = date.plusYears(2000);

        }
        if (date.isAfter(LocalDate.now().plusYears(50))) {
            date = date.minusYears(100);
        }

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
