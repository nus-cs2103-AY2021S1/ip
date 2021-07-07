
package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

import duke.task.DukeDateTime;

import duke.utils.Messages;

/**
 * Represents a parser which will parse the valid datetime format that the user has inputted into a
 * {@link DukeDateTime} object.
 */

public class DateTimeParser {
    private static final String[] DATE_TIME_PATTERNS_WITH_TIME = {"d MMM yyyy H:mm", "d MMM yyyy Hmm",
        "d MMM yyyy h:mm a", "d MMMM yyyy H:mm", "d MMMM yyyy Hmm", "d-M-yyyy H:mm", "d-M-yyyy Hmm", "d-M-yyyy h:mm a",
        "d/M/yyyy H:mm", "d/M/yyyy Hmm", "d/M/yyyy h:mm a", "d/M/yyyy h:mma", "d/M/yyyy ha", "d/MMM/yyyy h:mma",
        "d/MMM/yyyy ha", "yyyy-M-d Hmm", "yyyy-M-d h:mm a"};

    /**
     * Parses the input into a {@link DukeDateTime} object.
     * @param input The date and/or time input
     * @return The DukeDateTime object
     * @throws DukeException If the user did not input in the accepted format.
     */
    public static DukeDateTime parseDateTime(String input) throws DukeException {
        int index = patternsWithTime(input);
        if (index != -1) {
            LocalDateTime dateTime = LocalDateTime.parse(input,
                    DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS_WITH_TIME[index]));
            return new DukeDateTime(dateTime);
        } else {
            assert index == -1;
            throw new DukeException(Messages.INVALID_DATE_TIME_FORMAT);
        }
    }

    private static int patternsWithTime(String input) {
        for (int i = 0; i < DATE_TIME_PATTERNS_WITH_TIME.length; i++) {
            try {
                LocalDateTime.parse(input, DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS_WITH_TIME[i]));
                return i;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return -1;
    }
}
