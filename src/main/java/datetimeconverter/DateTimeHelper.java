package datetimeconverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

/**
 * Smaller methods to assist with date-time conversion in DateTimeConverter class.
 */
public class DateTimeHelper {
    /**
     * Attempt to convert date input to English Date format (E.g Saturday, March 02, 2019).
     *
     * @param input Date input.
     * @return
     */
    public static String tryConvertingToEnglishDate(String input) {
        try {
            LocalDate date = null;
            input = input.toLowerCase();
            if (input.equals("mon") || input.equals("monday")) {
                date = LocalDate.now().with(next(MONDAY));
            } else if (input.equals("tue") || input.equals("tuesday")) {
                date = LocalDate.now().with(next(TUESDAY));
            } else if (input.equals("wed") || input.equals("wednesday")) {
                date = LocalDate.now().with(next(WEDNESDAY));
            } else if (input.equals("thurs") || input.equals("thursday")) {
                date = LocalDate.now().with(next(THURSDAY));
            } else if (input.equals("fri") || input.equals("friday")) {
                date = LocalDate.now().with(next(FRIDAY));
            } else if (input.equals("sat") || input.equals("saturday")) {
                date = LocalDate.now().with(next(SATURDAY));
            } else if (input.equals("sun") || input.equals("sunday")) {
                date = LocalDate.now().with(next(SUNDAY));
            }
            input = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH).format(date);
            return input;
        } catch (DateTimeParseException | NullPointerException e) {
            // Don't format input if the date and time format does not match any element in formatString
        }
        assert input instanceof String : input;
        return input;
    }

    /**
     * Attempt to convert date-time input to English DateTime format (E.g Saturday, March 02, 2019 06:00 PM).
     *
     * @param input Date-Time input.
     * @return
     */
    public static String tryConvertingToEnglishDateTime(String input) {
        // Format the following date and time formats
        List<String> formatStrings = Arrays.asList(
                "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm", "dd/M/yyyy HHmm", "d/M/yyyy HHmm",
                "dd-MM-yyyy HHmm", "d-MM-yyyy HHmm", "dd-M-yyyy HHmm", "d-M-yyyy HHmm");

        DateTimeFormatter formatter;
        LocalDateTime dateTime;

        // Try to fit the input string into a format in formatString
        for (String formatString : formatStrings) {
            try {
                formatter = DateTimeFormatter.ofPattern(formatString);
                assert formatter instanceof DateTimeFormatter : formatter;
                dateTime = LocalDateTime.parse(input, formatter);
                assert dateTime instanceof LocalDateTime : dateTime;
                // E.g Convert 2/12/2019 1800 to Monday, December 02, 2019 06:00 PM
                input = dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm a"));
                assert input instanceof String : input;
            } catch (DateTimeParseException e) {
                // Don't format input if the date and time format does not match any element in formatString
            }
        }
        assert input instanceof String : input;
        return input;
    }
}
