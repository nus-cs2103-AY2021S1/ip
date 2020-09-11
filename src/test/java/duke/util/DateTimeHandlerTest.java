package duke.util;

import duke.DukeInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTimeHandlerTest {

    @Test
    public void dateFormat_validInput_correctDateFormat() {
        try {
            Assertions.assertEquals("01 Sep 2020", DateTimeHandler.parseDate("1-9-2020"));
            Assertions.assertEquals("01 Sep 2020", DateTimeHandler.parseDate("01-09-2020"));
        } catch (DukeInputException e) {
            Assertions.fail();
        }
    }

    @Test
    public void dateFormat_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> DateTimeHandler.parseDate("1/9/2020"));
        Assertions.assertThrows(DukeInputException.class, () -> DateTimeHandler.parseDate("01/09/2020"));
    }

    @Test
    public void dateTimeFormat_validInput_correctDateTimeFormat() {
        try {
            Assertions.assertEquals("01 Sep 2020, 08:00 AM", DateTimeHandler.parseDateTime("1-9-2020 0800"));
            Assertions.assertEquals("01 Sep 2020, 06:00 PM", DateTimeHandler.parseDateTime("01-09-2020 1800"));
        } catch (DukeInputException e) {
            Assertions.fail();
        }
    }

    @Test
    public void dateTimeFormat_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> DateTimeHandler.parseDateTime("1/9/2020 1800"));
        Assertions.assertThrows(DukeInputException.class, () -> DateTimeHandler.parseDateTime("01-09-2020 6pm"));
    }
}
