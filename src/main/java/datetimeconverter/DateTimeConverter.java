package datetimeconverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Arrays;

public class DateTimeConverter {

    public static String formatDateTime(String input) {
        // Make a copy of the input string
        String result = input;
        // Format the following date and time formats
        List<String> formatStrings = Arrays.asList(
                "dd/MM/yyyy HHmm", "d/MM/yyyy HHmm",
                "dd-MM-yyyy HHmm", "d-MM-yyyy HHmm",
                "dd/M/yyyy HHmm", "d/M/yyyy HHmm",
                "dd-M-yyyy HHmm", "d-M-yyyy HHmm");

        DateTimeFormatter formatter;
        LocalDateTime dateTime;

        // Try to fit the input string into a format in formatString
        for (String formatString : formatStrings) {
            try {
                formatter = DateTimeFormatter.ofPattern(formatString);
                dateTime = LocalDateTime.parse(result, formatter);
                // E.g Convert 2/12/2019 1800 to Monday, December 02, 2019 06:00 PM
                result = dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm a"));
            } catch (DateTimeParseException e) {
                // Pass and don't format input if the date and time format does not match any element in formatString
            }
        }
        return result;
    }
}
