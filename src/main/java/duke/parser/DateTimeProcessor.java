package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * This processor parses strings and returns a neatly
 * formatted Date-Time String.
 */
public class DateTimeProcessor {

    private final ArrayList<String> possibleDateFormats = new ArrayList<>(
            Arrays.asList(
                    "dd/MM/yyyy", "yyyy/MM/dd", "MM/dd/yyyy",
                    "dd-MM-yyyy", "yyyy-MM-dd", "MM-dd-yyyy"
            )
    );

    private final ArrayList<String> possibleDateTimeFormats = new ArrayList<>(
            Arrays.asList(
                        "dd/MM/yyyy HHmm", "yyyy/MM/dd HHmm", "MM/dd/yyyy HHmm",
                        "HHmm dd/MM/yyyy", "HHmm yyyy/MM/dd", "HHmm MM/dd/yyyy",
                        "dd-MM-yyyy HHmm", "yyyy-MM-dd HHmm", "MM/dd/yyyy HHmm",
                        "HHmm dd-MM-yyyy", "HHmm yyyy-MM-dd", "HHmm MM-dd-yyyy"
            )
    );

    private Optional<LocalDateTime> parseDateTime(String input) {
        for (String format : possibleDateTimeFormats) {
            try {
                return Optional.of(LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format)));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return Optional.empty();
    }

    private Optional<LocalDate> parseDate(String input) {
        for (String format : possibleDateFormats) {
            try {
                return Optional.of(LocalDate.parse(input, DateTimeFormatter.ofPattern(format)));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return Optional.empty();
    }

    /**
     * The string passed as date and/or time to an Event or Deadline
     * can be formatted to a neater format if it matches a pattern,
     * such as dd/MM/yyyy HHmm.
     *
     * @param input The date-time string which may not be formatted properly.
     * @return The neatly formatted date-time string.
     */
    public String getParsedDate(String input) {
        assert input.length() > 0 : "Input string is empty";
        Optional<LocalDateTime> possibleDateTime = parseDateTime(input);
        Optional<LocalDate> possibleDate = parseDate(input);
        if (possibleDateTime.isPresent()) {
            LocalDateTime dateTime = possibleDateTime.get();
            Month month = dateTime.getMonth();
            int day = dateTime.getDayOfMonth();
            int year = dateTime.getYear();
            int hour = dateTime.getHour();
            int min = dateTime.getMinute();
            String meridian = hour > 12 ? "PM" : "AM";
            return month.toString() + " " + day + " "
                    + year + " " + (hour > 12 ? hour - 12 : hour)
                    + ":" + (min < 10 ? "0" + min : min) + " " + meridian;
        } else if (possibleDate.isPresent()) {
            LocalDate dateTime = possibleDate.get();
            Month month = dateTime.getMonth();
            int day = dateTime.getDayOfMonth();
            int year = dateTime.getYear();
            return month.toString() + " " + day + " " + year;
        } else {
            // If no possible format can be found, just return
            // the same string.
            return input;
        }
    }
}
