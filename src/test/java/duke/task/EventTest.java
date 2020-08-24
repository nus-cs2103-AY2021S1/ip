package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Tests Event.
 */
public class EventTest {
    /**
     * Test isDateEquals method.
     */
    @Test
    public void testIsDateEquals() {
        assertTrue(createEvent().isDateEqual(LocalDate.parse("2020-04-03")));
        assertFalse(createEvent().isDateEqual(LocalDate.parse("2020-04-08")));
    }

    /**
     * Returns an event.
     *
     * @return the event.
     */
    private Event createEvent() {
        return new Event("description", LocalDate.parse("2020-04-03"));
    }

    /**
     * Test getData method.
     */
    @Test
    public void testGetData() {
        assertEquals("E_0_description_2020-04-03", createEvent().getData().trim());
    }

    /**
     * Test toString method.
     */
    @Test
    public void testToString() {
        assertEquals("[E][\u2718] description (at: 03 Apr 2020)", createEvent().toString());
    }
}
