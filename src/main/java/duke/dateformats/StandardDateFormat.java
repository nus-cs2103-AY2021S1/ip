package duke.dateformats;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StandardDateFormat implements DateFormat {

    @Override
    public boolean check(String date) {
        try {
            LocalDate ld = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public LocalDate mapToLocalDate(String date) {
        try {
            LocalDate ld = LocalDate.parse(date);
            return ld;
        } catch (DateTimeParseException e) {
            assert false : "should check the date format before you map";
            return null;
        }

    }
}
