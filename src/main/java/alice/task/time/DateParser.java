package alice.task.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alice.command.InvalidCommandException;

/**
 * Represents a parser that parses date strings into a date format that ALICE understands.
 */
public class DateParser {
    /**
     * List of known date formats that ALICE accepts.
     **/
    private static final List<DateTimeFormatter> KNOWN_DATE_FORMATS = createDateFormats();

    /**
     * Creates the list of formatter that accepts a specified list of known date patterns.
     * This method should only be used once to initialise the formatters used by the parser.
     *
     * @return list of DateTimeFormatter with acceptable date format.
     */
    private static List<DateTimeFormatter> createDateFormats() {
        // List of acceptable date format with optional year/month
        List<String> knownDatePatterns = Arrays.asList(
                "d[/][-]M[[/][-]uuuu]", "M[/][-]d[[/][-]uuuu]",
                "uuuu[/][-]M[/][-]d", "d-MMM[-uuuu]",
                "d-MMMM[-uuuu]", "d"
        );

        List<DateTimeFormatter> knownFormats = new ArrayList<>();
        // Create a formatter for each known patterns to be used for parsing dates
        for (int i = 0; i < knownDatePatterns.size(); i++) {
            knownFormats.add(new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(knownDatePatterns.get(i))
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue())
                    .parseDefaulting(ChronoField.YEAR, LocalDateTime.now().getYear())
                    .toFormatter()
            );
        }
        return knownFormats;
    }

    /**
     * Parses the date input given by the user into the implied {@code LocalDate}.
     *
     * @param dateString the user input containing a date.
     * @return the {@code LocalDate} indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static LocalDate parse(String dateString) throws InvalidCommandException {
        for (int i = 0; i < KNOWN_DATE_FORMATS.size(); i++) {
            try {
                return LocalDate.parse(dateString, KNOWN_DATE_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // Ignore exception, fallthrough expected
            }
        }

        throw new InvalidCommandException("Invalid date!");
    }
}
