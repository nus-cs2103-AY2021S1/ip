package duke.parser;

import duke.exceptions.DukeException;

import duke.task.DukeDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final String[] DATE_TIME_PATTERNS_WITHOUT_TIME = {"d/M/yyyy", "d MMM yyyy", "d MMMM yyyy", "d-M-yyyy"
            , "yyyy-M-d", "MM d yyyy", "MMM d yyyy", "MMMM d yyyy"};
    private static final String[] DATE_TIME_PATTERNS_WITH_TIME = {"d/M/yyyy H:mm", "d/M/yyyy h:mm a", "d/M/yyyy Hmm",
            "d MMM yyyy H:mm", "d MMM yyyy h:mm a", "d MMM yyyy Hmm", "d MMMM yyyy H:mm", "d MMMM yyyy h:mm a",
            "d MMMM yyyy Hmm", "d-M-yyyy H:mm", "d-M-yyyy h:mm a", "d-M-yyyy Hmm", "yyyy-M-d H:mm",
            "yyyy-M-d h:mm a", "yyyy-M-d Hmm", "d/M/yyyy ha", "d/M/yyyy h:mma"};

    public static DukeDateTime parseDateTime(String input) throws DukeException {
        int index = patternsWithTime(input);
        if (index != -1) {
            LocalDateTime dateTime = LocalDateTime.parse(input,
                    DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS_WITH_TIME[index]));
            return new DukeDateTime(dateTime, true);
        } else {
            index = patternsWithoutTime(input);
            if (index != -1) {
                LocalDate date = LocalDate.parse(input,
                        DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS_WITHOUT_TIME[index]));
                LocalDateTime dateTime = date.atStartOfDay();
                return new DukeDateTime(dateTime, false);
            } else {
                throw new DukeException("Invalid Date and Time!");
            }
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

    private static int patternsWithoutTime(String input) {
        for (int i = 0; i < DATE_TIME_PATTERNS_WITHOUT_TIME.length; i++) {
            try {
                LocalDateTime.parse(input, DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS_WITHOUT_TIME[i]));
                return i;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return -1;
    }

}