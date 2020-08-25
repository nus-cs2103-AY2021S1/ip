import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the handling of dates and times.
 */
public class DateTimeHandler {

    /**
     * Parses and formats the date string.
     *
     * @param date Unformatted date string.
     * @return Date string in the format of "dd MMM YYYY".
     * @throws DukeException If date input is invalid.
     */
    public static String parseDate(String date) throws DukeException {
        try {
            // Parse input
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate inputDate = LocalDate.parse(date, inputFormatter);

            // Produce formatted output
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return inputDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter the date as \"dd-MM-yyyy\".\n");
        }
    }

    /**
     * Parses and formats the date and time string.
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
            throw new DukeException("Enter the date and time as \"dd-MM-yyyy HHmm\".\n");
        }
    }
}
