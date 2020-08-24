package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testIsDateEquals() {
        assertTrue(createEvent().isDateEqual(LocalDate.parse("2020-04-03")));
        assertFalse(createEvent().isDateEqual(LocalDate.parse("2020-04-08")));
    }

    private Event createEvent() {
        return new Event("description", LocalDate.parse("2020-04-03"));
    }

    @Test
    public void testGetData() {
        assertEquals("E_0_description_2020-04-03", createEvent().getData().trim());
    }

    @Test
    public void testToString() {
        assertEquals("[E][\u2713] description (at: 03 Apr 2020)", createEvent().toString());
    }
}
