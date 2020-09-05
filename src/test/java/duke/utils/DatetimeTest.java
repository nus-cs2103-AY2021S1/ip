package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import duke.exception.DukeParseException;

public class DatetimeTest {
    private static Stream<Arguments> getParseDateTimeString_correctFormat_arguments() {
        return Stream.of(
                Arguments.of("2019-19-02 0600", "yyyy-dd-MM HHmm",
                        LocalDateTime.of(2019, 2, 19, 6, 0)),
                Arguments.of("20 12 2020 21:30", "dd MM yyyy HH:mm",
                        LocalDateTime.of(2020, 12, 20, 21, 30))
        );
    }

    @ParameterizedTest
    @MethodSource("getParseDateTimeString_correctFormat_arguments")
    public void parseDateTimeString_correctFormat_success(
            String datetime, String pattern, LocalDateTime actual) throws DukeParseException {
        assertEquals(Datetime.parseDateTimeString(datetime, pattern), actual);
    }

    @Test
    public void parseDateTimeString_wrongFormat_exceptionThrown() {
        String pattern = "yyyy dd MM";
        try {
            LocalDateTime actual = LocalDateTime.of(2019, 2, 19, 19, 20);
            assertEquals(Datetime.parseDateTimeString("2019-19-02", pattern), actual);
            fail();
        } catch (DukeParseException exception) {
            String expected = String.format(
                    "Ensure the datetime passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }

    private static Stream<Arguments> getParseDateString_correctFormat_arguments() {
        return Stream.of(
                Arguments.of("2019-19-02", "yyyy-dd-MM",
                        LocalDate.of(2019, 2, 19)),
                Arguments.of("20 12 2020", "dd MM yyyy",
                        LocalDate.of(2020, 12, 20))
        );
    }

    @ParameterizedTest
    @MethodSource("getParseDateString_correctFormat_arguments")
    public void parseDateString_correctFormat_success(
            String date, String pattern, LocalDate actualDate) throws DukeParseException {
        LocalTime noon = LocalTime.of(12, 0);
        LocalDateTime actual = LocalDateTime.of(actualDate, noon);
        assertEquals(Datetime.parseDateString(date, pattern), actual);
    }

    @Test
    public void parseDateString_wrongFormat_exceptionThrown() {
        String pattern = "yyyy dd MM";
        try {
            LocalDate actual = LocalDate.of(2019, 2, 19);
            assertEquals(Datetime.parseDateString("2019-19-02", pattern), actual);
            fail();
        } catch (DukeParseException exception) {
            String expected = String.format(
                    "Ensure the date passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }

    private static Stream<Arguments> getParseTimeString_correctFormat_arguments() {
        return Stream.of(
                Arguments.of("0654", "HHmm",
                        LocalTime.of(6, 54)),
                Arguments.of("19-23", "HH-mm",
                        LocalTime.of(19, 23))
        );
    }

    @ParameterizedTest
    @MethodSource("getParseTimeString_correctFormat_arguments")
    public void parseTimeString_correctFormat_success(
            String time, String pattern, LocalTime actualTime) throws DukeParseException {
        LocalDateTime actual = LocalDateTime.of(LocalDate.now(), actualTime);
        assertEquals(Datetime.parseTimeString(time, pattern), actual);
    }

    @Test
    public void parseTimeString_wrongFormat_exceptionThrown() {
        String pattern = "HH mm";
        LocalTime time = LocalTime.of(18, 8);
        LocalDateTime actual = LocalDateTime.of(LocalDate.now(), time);
        try {
            assertEquals(Datetime.parseDateTimeString("18-08", pattern), actual);
            fail();
        } catch (DukeParseException exception) {
            String expected = String.format(
                    "Ensure the datetime passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }
}
