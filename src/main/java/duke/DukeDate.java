package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDate {
    final String formatPattern = "MMM d yyyy";
    String stringDate;
    LocalDate date;

    //stringDateTime in format yyyy-mm-dd
    public DukeDate(String stringDate) throws DukeException {
        try {
            this.stringDate = stringDate;
            this.date = LocalDate.parse(stringDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date!");
        }
    }

    public String getStringDate() {
        return this.stringDate;
    }

    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(formatPattern));
    }
}
