package duke.dateformats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import duke.exceptions.DateFormatException;


public class StandardDateFormat implements DateFormat {
    //Solution below adapted from https://stackoverflow.com/questions/40175196
    private static final DateTimeFormatter INPUTDATEFORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HH:mm")
            .optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_DAY, 0).toFormatter();

    @Override
    public boolean check(String date) {
        try {
            LocalDate ldt = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public LocalDateTime mapToLocalDateTime(String date) throws DateFormatException {
        try {
            LocalDateTime ld = LocalDateTime.parse(date, INPUTDATEFORMATTER);
            return ld;
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Date format is not valid.");
        }

    }
}
