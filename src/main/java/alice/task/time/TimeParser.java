package alice.task.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alice.command.InvalidCommandException;

/**
 * Represents a parser that parses time strings into a time format that ALICE understands.
 */
public class TimeParser {
    /**
     * List of known time formats that ALICE accepts.
     **/
    private static final List<DateTimeFormatter> KNOWN_TIME_FORMATS = createTimeFormats();

    /**
     * Creates the list of formatter that accepts a specified list of known time patterns.
     * This method should only be used once to initialise the formatters used by the parser.
     *
     * @return list of DateTimeFormatter with acceptable time format.
     */
    private static List<DateTimeFormatter> createTimeFormats() {
        // List of acceptable date format with optional year/month
        List<String> knownTimePatterns = Arrays.asList("HHmm", "h:mm[ ]a", "h[ ]a");

        List<DateTimeFormatter> knownFormats = new ArrayList<>();
        // Create a formatter for each known patterns to be used for parsing dates
        for (int i = 0; i < knownTimePatterns.size(); i++) {
            knownFormats.add(new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(knownTimePatterns.get(i))
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter()
            );
        }
        return knownFormats;
    }

    /**
     * Parses the time input given by the user into the implied {@code LocalTime}.
     * Returns a default {@code LocalTime} of midnight 12am, when timeString is empty.
     *
     * @param timeString the user input containing a date.
     * @return the {@code LocalTime} indicated by the user input.
     * @throws InvalidCommandException if the time input given by the user does not match any known patterns.
     */
    public static LocalTime parse(String timeString) throws InvalidCommandException {
        if (timeString.isBlank()) {
            return LocalTime.MIDNIGHT;
        }
        for (int i = 0; i < KNOWN_TIME_FORMATS.size(); i++) {
            try {
                return LocalTime.parse(timeString, KNOWN_TIME_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // Ignore exception, fallthrough expected
            }
        }
        throw new InvalidCommandException("Invalid datetime! Please use a recognisable time format");
    }
}
