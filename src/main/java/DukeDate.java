import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDate {
    final String formatPattern = "MMM d yyyy";
    LocalDate date;

    //stringDateTime in format yyyy-mm-dd
    public DukeDate(String stringDate) throws DateTimeParseException {
        this.date = LocalDate.parse(stringDate);
    }

    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(formatPattern));
    }
}
