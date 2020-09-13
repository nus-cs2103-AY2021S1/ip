import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.parser.TimeParser;

/**
 * TimeParser tests.
 */
public class TimeParserTest {

    @Test
    public void isDate_wrongFormat_false() {
        // No space
        assertFalse(TimeParser.isDate("20201111"));
        assertFalse(TimeParser.isDate("11112020"));

        // Using colon
        assertFalse(TimeParser.isDate("2020:11:11"));
        assertFalse(TimeParser.isDate("2020:1:1"));
        assertFalse(TimeParser.isDate("20:11:2011"));
        assertFalse(TimeParser.isDate("2:1:2011"));

        // Extra number
        assertFalse(TimeParser.isDate("202011111"));
        assertFalse(TimeParser.isDate("2020 11111"));
        assertFalse(TimeParser.isDate("2020/11/111"));
        assertFalse(TimeParser.isDate("2020-111-11"));
        assertFalse(TimeParser.isDate("11111 2020"));
        assertFalse(TimeParser.isDate("11/111/2020"));
        assertFalse(TimeParser.isDate("111-11-2020"));

        // Not enough number
        assertFalse(TimeParser.isDate("2020111"));
        assertFalse(TimeParser.isDate("2020/1/11"));
        assertFalse(TimeParser.isDate("2020/01/1"));
        assertFalse(TimeParser.isDate("2020 01 1"));
        assertFalse(TimeParser.isDate("01 1 2020"));
        assertFalse(TimeParser.isDate("01-1-2020"));

        // Using character
        assertFalse(TimeParser.isDate("0c-10-2020"));
        assertFalse(TimeParser.isDate("01-c0-2020"));
        assertFalse(TimeParser.isDate("01-10-2c20"));
        assertFalse(TimeParser.isDate("2001-10-c0"));

        // Others
        assertFalse(TimeParser.isDate("2001-10-10."));
        assertFalse(TimeParser.isDate(".2001-10-10"));


    }

    @Test
    public void isDate_correctFormat_true() {
        char[] separators = new char[]{'.', '-', '/', ' '};
        String day = "01";
        String month = "11";
        String year = "2020";

        // Exhaustively test correct format
        for (char s1 : separators) {
            for (char s2: separators) {
                // YYYY MM DD
                String testStr = year;
                testStr += s1;
                testStr += month;
                testStr += s2;
                testStr += day;
                if (!TimeParser.isDate(testStr)) {
                    fail(testStr);
                }
                // DD MM YYYY
                testStr = day;
                testStr += s1;
                testStr += month;
                testStr += s2;
                testStr += year;
                if (!TimeParser.isDate(testStr)) {
                    fail(testStr);
                }
            }
        }
    }

    @Test
    public void isDateTime_wrongFormat_false() {
        // No space
        assertFalse(TimeParser.isDateTime("202010101111"));
        assertFalse(TimeParser.isDateTime("101011112020"));

        // Wrong delimiter
        assertFalse(TimeParser.isDateTime("2020c10c10c11c11"));
        assertFalse(TimeParser.isDateTime("10c10c11c11c2020"));

        // Extra number
        assertFalse(TimeParser.isDateTime("2020 10 101 1111"));
        assertFalse(TimeParser.isDateTime("2020 110 01 1111"));
        assertFalse(TimeParser.isDateTime("20210 10 01 1111"));
        assertFalse(TimeParser.isDateTime("2021 10 01 11111"));
        assertFalse(TimeParser.isDateTime("2020 10 101 1111"));
        assertFalse(TimeParser.isDateTime("2021 101 01 2020"));

        // Not enough number
        assertFalse(TimeParser.isDateTime("2020 10 10 111"));
        assertFalse(TimeParser.isDateTime("2020 10 1 1111"));
        assertFalse(TimeParser.isDateTime("2020 1 10 1111"));
        assertFalse(TimeParser.isDateTime("202 10 10 1111"));

        // Others
        assertFalse(TimeParser.isDateTime("2020 10 10 11-11"));
        assertFalse(TimeParser.isDateTime("2020 10 10.11 11"));
    }

    @Test
    public void isDateTime_correctFormat_true() {
        char[] dateSeparators = new char[]{'.', '-', '/', ' '};
        char[] dateTimeSeparators = new char[]{' ', 'T'};
        char[] timeSeparators = new char[]{' ', '.', ':'};
        String day = "01";
        String month = "11";
        String year = "2020";
        String hour = "10";
        String min = "10";

        // Exhaustively test correct format
        for (char s1 : dateSeparators) {
            for (char s2: dateSeparators) {
                for (char s3 : dateTimeSeparators) {
                    for (char s4 : timeSeparators) {
                        // YYYY MM DD HH MM
                        String testStr = year + s1 + month + s2 + day + s3 + hour + s4 + min;
                        if (!TimeParser.isDateTime(testStr)) {
                            fail(testStr);
                        }
                        // DD MM YYYY HH MM
                        testStr = day + s1 + month + s2 + year + s3 + hour + s4 + min;
                        if (!TimeParser.isDateTime(testStr)) {
                            fail(testStr);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void parseDate_correctInput_correctLocalDateReturned() {
        char[] separators = new char[]{'.', '-', '/', ' '};
        String day = "01";
        int dayInt = 1;
        String month = "11";
        int monthInt = 11;
        String year = "2020";
        int yearInt = 2020;

        // Exhaustively test correct format
        for (char s1 : separators) {
            for (char s2: separators) {
                // YYYY MM DD
                String testStr = year;
                testStr += s1;
                testStr += month;
                testStr += s2;
                testStr += day;
                if (!TimeParser.parseDate(testStr)
                        .equals(LocalDate.of(yearInt, monthInt, dayInt))) {
                    fail(testStr);
                }
                // DD MM YYYY
                testStr = day;
                testStr += s1;
                testStr += month;
                testStr += s2;
                testStr += year;
                if (!TimeParser.parseDate(testStr)
                        .equals(LocalDate.of(yearInt, monthInt, dayInt))) {
                    fail(testStr);
                }
            }
        }
    }

    @Test
    public void parseDate_invalidInput_exceptionThrown() {
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDate("2020 19 10"));
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDate("2020-10-99"));
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDate("10-19-2000"));
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDate("10-19-2020"));
    }

    @Test
    public void parseDateTime_validInput_correctDateTimeReturned() {
        char[] dateSeparators = new char[]{'.', '-', '/', ' '};
        char[] dateTimeSeparators = new char[]{' ', 'T'};
        char[] timeSeparators = new char[]{' ', '.', ':'};
        String day = "01";
        int dayInt = 1;
        String month = "11";
        int monthInt = 11;
        String year = "2020";
        int yearInt = 2020;
        String hour = "10";
        int hourInt = 10;
        String min = "10";
        int minInt = 10;

        // Exhaustively test correct format
        for (char s1 : dateSeparators) {
            for (char s2: dateSeparators) {
                for (char s3 : dateTimeSeparators) {
                    for (char s4 : timeSeparators) {
                        // YYYY MM DD HH MM
                        String testStr = year + s1 + month + s2 + day + s3 + hour + s4 + min;
                        if (!TimeParser.parseDateTime(testStr)
                                .equals(LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minInt))) {
                            fail(testStr);
                        }
                        // DD MM YYYY HH MM
                        testStr = day + s1 + month + s2 + year + s3 + hour + s4 + min;
                        if (!TimeParser.parseDateTime(testStr)
                                .equals(LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minInt))) {
                            fail(testStr);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void parseDateTime_invalidInput_exceptionThrown() {
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDateTime("2020 19 10 22 99"));
        assertThrows(DukeIllegalArgumentException.class, () -> TimeParser.parseDateTime("2020 19 10 99 11"));
    }
}
