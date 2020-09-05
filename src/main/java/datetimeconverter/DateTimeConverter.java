package datetimeconverter;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Accept dates in formats like dd-mm-yyyy and convert it to English date format.
 */
public class DateTimeConverter {

    /**
     * Accept dates in "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm", "dd-MM-yyyy HHmm", "d-MM-yyyy HHmm",
     * "dd/M/yyyy HHmm", "d/M/yyyy HHmm", "dd-M-yyyy HHmm", "d-M-yyyy HHmm" format and convert
     * it to English date-time format (E.g Saturday, March 02, 2019 06:00 PM). It can also accept
     * actual days of the week, specifically short word forms ("mon") and exact word
     * forms ("Monday"), and convert them to English date format.
     *
     * @param input Date-Time pattern.
     * @return English date format.
     * @throws DateTimeParseException If unable to convert Date-Time pattern to English date format.
     *                                If that happens, String input to formatDateTime(...) = String
     *                                output from formatDateTime(...).
     */
    public static String formatDateTime(String input) {
        List<String> formatDays = Arrays.asList(
                "mon", "tue", "wed", "thurs", "fri", "sat", "sun",
                "Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun",
                "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday",
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        if (formatDays.contains(input)) {
            return DateTimeHelper.tryConvertingToEnglishDate(input);
        } else {
            return DateTimeHelper.tryConvertingToEnglishDateTime(input);
        }
    }
}
