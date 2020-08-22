package duke.utils;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DatetimeTest {
    @Test
    public void parseDateTimeString_correctFormat_success() throws DukeException {
        LocalDate actual = LocalDate.of(2019, 2, 19);
        assertEquals(Datetime.parseDateTimeString("2019-19-02", "yyyy-dd-MM"), actual);
    }

    @Test
    public void parseDateTimeString_wrongFormat_exceptionThrown() {
        String pattern = "yyyy dd MM";
        try {
            LocalDate actual = LocalDate.of(2019, 2, 19);
            assertEquals(Datetime.parseDateTimeString("2019-19-02", pattern), actual);
            fail();
        } catch (DukeException exception) {
            String expected = String.format(
                    "Apologies. Ensure the datetime passed in is of the form: '%s'.", pattern);
            assertEquals(expected, exception.getMessage());
        }
    }
}
