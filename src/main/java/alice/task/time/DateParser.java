package alice.task.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
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
                "d-MMMM[-uuuu]"
        );

        LocalDateTime currTime = LocalDateTime.now();
        List<DateTimeFormatter> knownFormats = new ArrayList<>();
        // Create a formatter for each known patterns to be used for parsing dates
        for (int i = 0; i < knownDatePatterns.size(); i++) {
            knownFormats.add(new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(knownDatePatterns.get(i))
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, currTime.getMonthValue())
                    .parseDefaulting(ChronoField.YEAR, currTime.getYear())
                    .toFormatter()
            );
        }
        return knownFormats;
    }

    /**
     * Parse a special datetime language that represents a date and time to the implied {@code LocalDateTime}.
     * For example, Tonight implies today night.
     *
     * @param dateTimeString the dateTimeString with a possible grammar that specifies both date and time.
     * @param currDateTime   the current datetime to be referenced against. Usually contains {@code LocalDateTime.now()}
     * @return the implied {@code LocalDateTime}, or null if no {@code LocalDateTime} detected.
     * @throws InvalidCommandException if an internal error occur.
     */
    public static LocalDateTime parseSpecial(String dateTimeString, LocalDateTime currDateTime)
            throws InvalidCommandException {
        NaturalDay dateTime = NaturalDay.parseDateTime(dateTimeString);

        if (dateTime == null) {
            return null;
        }

        switch (dateTime) {
        case NOW:
            return currDateTime;
        case TONIGHT:
            return LocalDateTime.of(currDateTime.toLocalDate(), TimeParser.parse("NIGHT"));
        default:
            return null;
        }
    }

    /**
     * Parses the date input given by the user into the implied {@code LocalDate}.
     *
     * @param dateString the user input containing a date.
     * @param currDate   the current date to be referenced against. Usually contains {@code LocalDate.now()}
     * @return the {@code LocalDate} indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static LocalDate parse(String dateString, LocalDate currDate) throws InvalidCommandException {
        assert !dateString.isBlank() : "dateString should not be empty when using LocalDate.parse";

        LocalDate formattedDate = parseByDateFormat(dateString);
        if (formattedDate != null) {
            return formattedDate;
        }

        LocalDate naturalDate = parseByNaturalDay(dateString, currDate);
        if (naturalDate != null) {
            return naturalDate;
        }

        throw new InvalidCommandException("Invalid date! Please use a recognisable date format");
    }

    /**
     * Parse the date input by checking with predefined date formats.
     * Expects the date to match one of the formatter in {@code KNOWN_DATE_FORMATS}.
     *
     * @param dateString the {@code LocalDate} indicated by the user input.
     * @return the {@code LocalDate} that is represented by the user,
     * or null if the string does not match any known natural date language.
     */
    static LocalDate parseByDateFormat(String dateString) {
        for (int i = 0; i < KNOWN_DATE_FORMATS.size(); i++) {
            try {
                return LocalDate.parse(dateString, KNOWN_DATE_FORMATS.get(i));
            } catch (DateTimeParseException ex) {
                // Ignore exception, fallthrough expected
            }
        }
        return null;
    }

    /**
     * Parse the date input by checking with known natural date language.
     * Expects the time to match one of the date language defined in {@code NaturalDay}.
     *
     * @param dateString the {@code LocalDate} indicated by the user input.
     * @param currDate   the current date to be referenced against. Usually contains {@code LocalDate.now()}
     * @return the {@code LocalDate} that is represented by the user,
     * or null if the string does not match any known natural date language.
     */
    static LocalDate parseByNaturalDay(String dateString, LocalDate currDate) {
        // Parse for natural days
        NaturalDay day = NaturalDay.parseDate(dateString);

        if (day == null) {
            return null;
        }

        switch (day) {
        case TODAY:
            return currDate;
        case TOMORROW:
            return currDate.plusDays(1);
        case MONDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        case TUESDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        case WEDNESDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        case THURSDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        case FRIDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        case SATURDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        case SUNDAY:
            return currDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        default:
            return null;
        }
    }
}
