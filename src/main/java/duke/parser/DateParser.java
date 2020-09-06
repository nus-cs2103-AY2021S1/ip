package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Parses a date string to a <code>LocalDate</code> object.
 */
public class DateParser {
    /**
     * Parses a date string to a <code>LocalDate</code> object.
     * @param dateStr The string representing the date
     * @return An <code>Optional</code> of <code>LocalDate</code> parsed.
     */
    public static Optional<LocalDate> parse(String dateStr) {
        String[] formats = {"yyyy-MM-dd",
                            "yyyy-M-dd",
                            "yyyy-M-d",
                            "dd-MM-yyyy",
                            "dd-M-yyyy",
                            "d-M-yyyy",
                            "yyyy/MM/dd",
                            "dd/MM/yyyy",
                            "dd/M/yyyy",
                            "d/M/yyyy"
        };
        for (String format : formats) {
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
                return Optional.of(date);
            } catch (DateTimeParseException e) {
                // exception is thrown means that cannot parse date in that format
                // if cannot parse date in any format, simply return an empty optional
            }
        }
        return Optional.empty();
    }
}
