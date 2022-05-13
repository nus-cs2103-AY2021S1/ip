package duke.dateformats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import duke.exceptions.DateFormatException;

public class StandardTimeFormat implements DateFormat {
    //Solution below adapted from https://stackoverflow.com/questions/40175196
    private static final DateTimeFormatter INPUTDATEFORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                    .optionalStart().appendPattern(" HH:mm")
                    .toFormatter();
    @Override
    public boolean check(String date) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(date, INPUTDATEFORMATTER);
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
