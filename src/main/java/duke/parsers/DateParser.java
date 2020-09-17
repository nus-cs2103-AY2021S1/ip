package duke.parsers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import duke.dateformats.DateFormat;
import duke.dateformats.DayOnlyFormat;
import duke.dateformats.StandardDateFormat;
import duke.dateformats.StandardTimeFormat;
import duke.exceptions.DateFormatException;
import duke.exceptions.DukeException;


public class DateParser implements Parser<LocalDateTime> {
    private static final List<DateFormat> DATE_TIME_FORMAT_LIST =
            Arrays.asList(new DayOnlyFormat(), new StandardDateFormat(), new StandardTimeFormat());
    private String date;

    public DateParser(String date) {
        this.date = date;
    }

    @Override
    public LocalDateTime parse() throws DukeException {
        LocalDateTime ldt;
        for (DateFormat format: DATE_TIME_FORMAT_LIST) {
            if (format.check(date)) {
                ldt = format.mapToLocalDateTime(date);
                return ldt;
            }
        }
        throw new DateFormatException("The date format is not valid.");
    }
}
