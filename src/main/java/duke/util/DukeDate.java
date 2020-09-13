package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DukeDate {
    private LocalDate localDate;

    /**
     * Constructor.
     * @param datetimeString raw string containing the date time from user input.
     * @throws DukeException
     */
    public DukeDate(String datetimeString) throws DukeException {
        String[] datetimeArray = datetimeString.split(" ");
        try {
            this.localDate = LocalDate.parse(datetimeArray[0].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("I can't really understand the due dates.");
        }
    }

    /**
     * String representation of the datetime format for output.
     * @return string representation.
     */
    @Override
    public String toString() {
        String day = String.valueOf(localDate.getDayOfMonth());
        String monthString = String.valueOf(localDate.getMonth());
        String year = String.valueOf(localDate.getYear());
        return day + " " + monthString + " " + year;
    }
}
