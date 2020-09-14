package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

enum DateFormat {
    FORMAT1("d-M-yy"),
    FORMAT2("d/M/yy"),
    FORMAT3("d-M-yyyy"),
    FORMAT4("d/M/yyyy"),
    FORMAT5("d MMM yyyy"),
    FORMAT6("yyyy-MM-dd");

    private final String pattern;

    DateFormat(String pattern) {
        this.pattern = pattern;
    }

    // Solution below adapted from https://stackoverflow.com/a/4024604
    /**
     * Converts a given string into a LocalDate if and only if it matches at least
     * one of the accepted date formats.
     *
     * @param date String that represents a date.
     * @return LocalDate resulting from the parsing of <code>date</code> into a DateTimeFormatter.
     * @throws InvalidDateException If the string is not in an acceptable date format.
     */
    public static LocalDate getLocalDate(String date) throws InvalidDateException {
        LocalDate out = null;

        for (DateFormat format : DateFormat.values()) {
            try {
                out = LocalDate.parse(date, format.toDateFormat());
            } catch (DateTimeParseException ignored) {
                // This date format does not work, better try the next.
            }
        }

        if (out == null) {
            throw new InvalidDateException();
        }

        return out;
    }

    /**
     * Converts the DateFormat into a DateTimeFormatter. This is used
     * for parsing into LocalDate objects.
     *
     * @return DateTimeFormatter representing the DateFormat.
     */
    DateTimeFormatter toDateFormat() {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
