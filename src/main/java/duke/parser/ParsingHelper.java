package duke.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class containing helper functions used by several commands in the Parser class.
 */
class ParsingHelper {

    /**
     * Splits a string into 2 around the first occurrence of a regex pattern. An exception is thrown if the pattern does
     * not exist, or if either of the tokens are blank.
     */
    static String[] splitAround(String string, String pattern, String errorMessage) throws DukeParsingException {
        int splitSize = 2;
        String[] argSplit = string.split(pattern, splitSize);

        if (argSplit.length != splitSize) {
            throw new DukeParsingException(errorMessage);
        }

        for (String s : argSplit) {
            if (s.isBlank()) {
                throw new DukeParsingException(errorMessage);
            }
        }
        return argSplit;
    }

    static Date parseDate(String dateString) throws DukeParsingException {
        final DateFormat dateOnly = new SimpleDateFormat("d/M/y");
        // TODO: improve time parsing - this accepts nonsense time formats eg. 27:00. Hm also does not work for some
        // reason.
        final DateFormat withTime = new SimpleDateFormat("d/M/y H:m");

        try {
            return withTime.parse(dateString);
        } catch (ParseException e) {
            // ignore, because we want to try parsing with date only after this
        }

        try {
            return dateOnly.parse(dateString);
        } catch (ParseException e) {
            throw new DukeParsingException("Invalid date format! I only understand dates in the format day/month/year"
                    + " 24h time or day/month/year. Eg. 15/01/2020 17:00 for 15th January 5pm");
        }

    }

    static void ensureNoArgs(String args, String commandName) throws DukeParsingException {
        if (!args.isBlank()) {
            throw new DukeParsingException(String.format("I don't understand that. Did you mean %s?", commandName));
        }
    }

    static void ensureArgsPresent(String args, String errorString) throws DukeParsingException {
        if (args.isBlank()) {
            throw new DukeParsingException(errorString);
        }
    }

    static int parseTaskNumber(String args, String taskDescription, String example)
            throws DukeParsingException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new DukeParsingException(String.format("You need to tell me the number of the task %s. Eg. %s",
                    taskDescription, example));
        }
    }
}
