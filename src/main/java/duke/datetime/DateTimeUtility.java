package duke.datetime;

import duke.Duke;
import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
    private static DateTimeFormatter OUTPUT_DATE_FMT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static DateTimeFormatter OUTPUT_DATETIME_FMT = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a");

    /**
     * Checks whether the input dataString is of the correct Date or DateTime format, or is just a String.
     *
     * @param dateStr
     * @return the format of the input dataString (whether it's of a Date,  DateTime or normal String format)
     */
    public static DateTimeFormat checkDateTimeType(String dateStr) {
        assert !dateStr.isEmpty() : "dateStr should not be empty";
        try {
            LocalDateTime.parse(dateStr);
            return DateTimeFormat.DateTime;
        } catch (DateTimeException e) {}
        try {
            LocalDateTime.parse(dateStr, OUTPUT_DATETIME_FMT);
            return DateTimeFormat.DateTime;
        } catch (DateTimeException e) {}
        try {
            LocalDate.parse(dateStr);
            return DateTimeFormat.Date;
        } catch (DateTimeException e) {}
        try {
            LocalDate.parse(dateStr, OUTPUT_DATE_FMT);
            return DateTimeFormat.Date;
        } catch (DateTimeException e) {}

        return DateTimeFormat.String;
    }

    /**
     * Formats the input string according to the DateTimeFormat specified. If it is of a Date or DateTime
     * format, output a String that changes the input string to a specified output format. If it is of a
     * String format, formatString returns the same string.
     *
     * @param dateStr
     * @param format format of the input string that can be obtained with checkDateTimeType()
     * @return Another string of the correct standardised output format
     */

    public static String formatString(String dateStr, DateTimeFormat format) throws DukeException {
        assert !dateStr.isEmpty() : "dateStr should not be empty";
        assert format != null : "format cannot be null";

        switch(format) {
        case DateTime:
            try {
                return LocalDateTime.parse(dateStr).format(OUTPUT_DATETIME_FMT);
            } catch (DateTimeException e) {
                throw new DukeException("Datetime format " + format
                                         + " does not match that of input string " + dateStr);
            }
        case Date:
            try {
                return LocalDate.parse(dateStr).format(OUTPUT_DATE_FMT);
            } catch (DateTimeException e) {
                throw new DukeException("Datetime format " + format
                                         + " does not match that of input string " + dateStr);
            }

        default:
            return dateStr;
        }
    }

    /**
     * Formats the input string according to the DateTimeFormat that is Automatically detected
     * using checkDateTimeType().
     *
     * @param dateStr
     * @return
     */
    public static String formatString(String dateStr) {
        try {
            return DateTimeUtility.formatString(dateStr, DateTimeUtility.checkDateTimeType(dateStr));
        } catch (DukeException e) {
            return dateStr;
        }

    }

    public static int compare(LocalDate a, LocalDate b) {
        assert a != null : "a cannot be null";
        assert b != null : "b cannot be null";
        return a.compareTo(b);
    }

    public static int compare(LocalDateTime a, LocalDateTime b) {
        assert a != null : "a cannot be null";
        assert b != null : "b cannot be null";
        return a.compareTo(b);
    }

    public static int compare(LocalDate a, LocalDateTime b) {
        assert a != null : "a cannot be null";
        assert b != null : "b cannot be null";
        return a.compareTo(b.toLocalDate());
    }

    public static int compare(LocalDateTime a, LocalDate b) {
        assert a != null : "a cannot be null";
        assert b != null : "b cannot be null";
        return a.toLocalDate().compareTo(b);
    }

    /**
     * Overloaded comparator between two input DateTime strings. Checks the DateTimeFormat for each
     * string and makes the right comparisons between Date and DateTime. If either one of the strings
     * cannot be parsed as a Date or DateTime object, throws an exception.
     *
     * @param a
     * @param b
     * @return
     * @throws DateTimeException
     */
    public static int compare(String a, String b) throws DateTimeException {
        assert !a.isEmpty() : "a cannot be empty";
        assert !b.isEmpty() : "b cannot be empty";

        DateTimeFormat aType = DateTimeUtility.checkDateTimeType(a);
        DateTimeFormat bType = DateTimeUtility.checkDateTimeType(b);

        if (aType == DateTimeFormat.Date) {
            if (bType == DateTimeFormat.Date) {
                return DateTimeUtility.compare(LocalDate.parse(a, OUTPUT_DATE_FMT), LocalDate.parse(b, OUTPUT_DATE_FMT));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDate.parse(a, OUTPUT_DATE_FMT), LocalDateTime.parse(b, OUTPUT_DATETIME_FMT));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else if (aType == DateTimeFormat.DateTime) {
            if (bType == DateTimeFormat.Date) {
                return DateTimeUtility.compare(LocalDateTime.parse(a, OUTPUT_DATE_FMT), LocalDate.parse(b, OUTPUT_DATE_FMT));
            } else if (bType == DateTimeFormat.DateTime) {
                return DateTimeUtility.compare(LocalDateTime.parse(a, OUTPUT_DATETIME_FMT), LocalDateTime.parse(b, OUTPUT_DATETIME_FMT));
            } else {
                throw new DateTimeException("Cannot compare Datetime with string!");
            }
        } else {
            throw new DateTimeException("Cannot compare Datetime with string!");
        }
    }
}
