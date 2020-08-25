package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to convert between a String object and LocalDate object
 */
public class DukeDate {
    final String formatPattern = "MMM d yyyy";
    String stringDate;
    LocalDate date;

    /**
     * Constructor
     * @param stringDate a String date in format yyyy-mm-dd
     * @throws DukeException
     */
    public DukeDate(String stringDate) throws DukeException {
        try {
            this.stringDate = stringDate;
            this.date = LocalDate.parse(stringDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date!");
        }
    }

    /**
     * Getter for the String format of the date
     * @return String representing the date
     */
    public String getStringDate() {
        return this.stringDate;
    }

    /**
     * String representation of date in format formatPattern provided
     * @return String representation of date in format formatPattern provided
     */
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(formatPattern));
    }
}
