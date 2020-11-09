package duke.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class DukeDateTimeTest {

    @Test
    void constructor_invalidFormat_exceptionThrown() {
        try {
            new DukeDateTime("20200101 1800");
            fail();
        } catch (DateTimeParseException e) {
            return;
        }
        fail();
    }

    @Test
    void constructor_emptyString_exceptionThrown() {
        try {
            new DukeDateTime("");
            fail();
        } catch (DateTimeParseException e) {
            return;
        }
        fail();
    }

    @Test
    void testToString() {
        String input = "02022020 1800";
        assertEquals(input, new DukeDateTime(input).toString());
    }

}
