package duke.util;

import duke.util.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    void constructor_emptyString_failure() {
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