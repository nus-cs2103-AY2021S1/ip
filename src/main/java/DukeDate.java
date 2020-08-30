import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DukeDate {
    private LocalDate localDate;

    public DukeDate(String datetimeString) throws DukeException {
        String[] datetimeArray = datetimeString.split(" ");
        try {
            this.localDate = LocalDate.parse(datetimeArray[0].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("I can't really understand the due dates.");
        }
    }

    @Override
    public String toString() {
        String day = String.valueOf(localDate.getDayOfMonth());
        String monthString = String.valueOf(localDate.getMonth());
        String year = String.valueOf(localDate.getYear());
        return day + " " + monthString + " " + year;
    }
}
