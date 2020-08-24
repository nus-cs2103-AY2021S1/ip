package duke.parsers;

import duke.exceptions.DukeDateTimeParseException;
import duke.utils.DukeDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents a parser that parses String inputs in the accepted formats into a
 * {@link DukeDateTime} object. */
public class DukeDateTimeParser {

    private static final String[] PATTERNS = {"d/M/yyyy", "d MMM yyyy",
            "d MMMM yyyy", "d-M-yyyy", "yyyy-M-d"};
    private static final String[] PATTERNS_WITH_TIME = {"d/M/yyyy H:mm", "d/M/yyyy h:mm a", "d/M/yyyy Hmm",
            "d MMM yyyy H:mm", "d MMM yyyy h:mm a", "d MMM yyyy Hmm",
            "d MMMM yyyy H:mm", "d MMMM yyyy h:mm a", "d MMMM yyyy Hmm",
            "d-M-yyyy H:mm", "d-M-yyyy h:mm a", "d-M-yyyy Hmm",
            "yyyy-M-d H:mm", "yyyy-M-d h:mm a", "yyyy-M-d Hmm"};

    /** Parses the specified input into a {@link DukeDateTime} object.
     *
     * @param input The user input of the date and/or time.
     * @return The DukeDateTime object.
     * @throws DukeDateTimeParseException If the user input is not in an accepted format.
     */
    public static DukeDateTime parse(String input) throws DukeDateTimeParseException {
        int index = patternWithTimeIndex(input);
        if (index != -1) {
            LocalDateTime dateTime = LocalDateTime.parse(input,
                    DateTimeFormatter.ofPattern(PATTERNS_WITH_TIME[index]));
            return new DukeDateTime(dateTime, true);
        } else {
            index = patternIndex(input);
            if (index != -1) {
                LocalDate date = LocalDate.parse(input,
                        DateTimeFormatter.ofPattern(PATTERNS[index]));
                LocalDateTime dateTime = date.atStartOfDay();
                return new DukeDateTime(dateTime, false);
            } else {
                throw new DukeDateTimeParseException();
            }
        }
    }

    private static int patternWithTimeIndex(String input) {
        for (int i = 0; i < PATTERNS_WITH_TIME.length; i++) {
            try {
                LocalDateTime.parse(input, DateTimeFormatter.ofPattern(PATTERNS_WITH_TIME[i]));
                return i;
            } catch (DateTimeParseException ignored) {

            }
        }
        return -1;
    }

    private static int patternIndex(String input) {
        for (int i = 0; i < PATTERNS.length; i++) {
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern(PATTERNS[i]));
                return i;
            } catch (DateTimeParseException ignored) {

            }
        }
        return -1;
    }

}
