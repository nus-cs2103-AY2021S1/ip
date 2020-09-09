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
        List<String> knownTimePatterns = Arrays.asList("HHmm", "h:mma", "ha");

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
     * Parse the time input given by the user into the implied {@code LocalTime}.
     *
     * @param timeString the user input containing a date.
     * @return the {@code LocalTime} indicated by the user input.
     * @throws InvalidCommandException if the time input given by the user does not match any known patterns.
     */
    public static LocalTime parse(String timeString) throws InvalidCommandException {
        assert !timeString.isBlank() : "timeString should not be empty when using LocalTime.parse";

        LocalTime formattedTime = parseByTimeFormat(timeString);
        if (formattedTime != null) {
            return formattedTime;
        }

        LocalTime naturalTime = parseByNaturalTime(timeString);
        if (naturalTime != null) {
            return naturalTime;
        }

        throw new InvalidCommandException("Invalid time! Please use a recognisable time format."
                + "\n Remember to include am/pm for 12h time format.");
    }

    /**
     * Parse the time input by checking with predefined time formats.
     * Expects the time to match one of the formatter in {@code KNOWN_TIME_FORMATS}.
     *
     * @param timeString the {@code LocalTime} indicated by the user input.
     * @return the {@code LocalTime} that is represented by the user,
     * or null if the string does not match any known format.
     */
    static LocalTime parseByTimeFormat(String timeString) {
        for (int i = 0; i < KNOWN_TIME_FORMATS.size(); i++) {
            try {
                return LocalTime.parse(timeString, KNOWN_TIME_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // Ignore exception, fallthrough expected
            }
        }
        return null;
    }

    /**
     * Parse the time input by checking with known natural time language.
     * Expects the time to match one of the time language defined in {@code NaturalDay}.
     *
     * @param timeString the {@code LocalTime} indicated by the user input.
     * @return the {@code LocalTime} that is represented by the user,
     * or null if the string does not match any known natural time language.
     */
    static LocalTime parseByNaturalTime(String timeString) {
        // Parse for natural days
        NaturalDay time = NaturalDay.parseTime(timeString);

        if (time == null) {
            return null;
        }

        switch (time) {
        case MORNING:
            // Alice default: 8am
            return LocalTime.of(8, 0);
        case NOON:
            // Alice default: 12pm
            return LocalTime.of(12, 0);
        case EVENING:
            // Alice default: 7pm
            return LocalTime.of(19, 0);
        case NIGHT:
            // Alice default: 10pm
            return LocalTime.of(22, 0);
        case MIDNIGHT:
            // Alice default: 11:59pm/ 2359
            return LocalTime.of(23, 59);
        default:
            return null;
        }
    }
}
