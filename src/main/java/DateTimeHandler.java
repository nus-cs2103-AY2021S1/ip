import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the handling of dates and times.
 */
public class DateTimeHandler {

    /**
     * Parses the date and time string into a format.
     *
     * @param dateTime Unformatted date and time string.
     * @return Date and time string in the format of "dd MMM YYYY, hh:ss".
     * @throws DukeException If date and time input is invalid.
     */
    public static String parseDateTime(String dateTime) throws DukeException {
        try {
            // Parse input
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime inputDateTime = LocalDateTime.parse(dateTime, inputFormatter);

            // Produce formatted output
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:ss a");
            return inputDateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("\u2639 OOPS!!! Enter the date and time as \"dd-MM-yyyy HHmm\".\n");
        }
    }
}
