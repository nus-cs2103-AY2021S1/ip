package duke.utils;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatetimeTest {
    @Test
    public void parseDateTimeString_correctFormat_success() throws DukeException {
        LocalDateTime actual = LocalDateTime.of(2019, 2, 19, 6, 0);
        assertEquals(Datetime.parseDateTimeString("2019-19-02 0600", "yyyy-dd-MM HHmm"), actual);
    }

    @Test
    public void parseDateTimeString_wrongFormat_exceptionThrown() {
        String pattern = "yyyy dd MM";
        try {
            LocalDateTime actual = LocalDateTime.of(2019, 2, 19, 19, 20);
            assertEquals(Datetime.parseDateTimeString("2019-19-02", pattern), actual);
            fail();
        } catch (DukeException exception) {
            String expected = String.format(
                    "Apologies. Ensure the datetime passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }

    @Test
    public void parseTimeString_correctFormat_success() throws DukeException {
        LocalTime time = LocalTime.of(6, 54);
        LocalDateTime actual = LocalDateTime.of(LocalDate.now(), time);
        assertEquals(Datetime.parseTimeString("0654", "HHmm"), actual);
    }

    @Test
    public void parseTimeString_wrongFormat_exceptionThrown() {
        String pattern = "HH mm";
        LocalTime time = LocalTime.of(18, 8);
        LocalDateTime actual = LocalDateTime.of(LocalDate.now(), time);
        try {
            assertEquals(Datetime.parseDateTimeString("18-08", pattern), actual);
            fail();
        } catch (DukeException exception) {
            String expected = String.format(
                    "Apologies. Ensure the datetime passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }
}
