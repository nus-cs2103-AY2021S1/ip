package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Tests Deadline.
 */
public class DeadlineTest {
    /**
     * Test isDateEqual method.
     */
    @Test
    public void testIsDateEquals() {
        assertTrue(createDeadline().isDateEqual(LocalDate.parse("2020-04-03")));
        assertFalse(createDeadline().isDateEqual(LocalDate.parse("2020-04-08")));
    }

    /**
     * Returns a deadline.
     *
     * @return the deadline.
     */
    private Deadline createDeadline() {
        return new Deadline("description", LocalDate.parse("2020-04-03"));
    }

    /**
     * Test getData method.
     */
    @Test
    public void testGetData() {
        assertEquals("D_0_description_2020-04-03", createDeadline().getData().trim());
    }

    /**
     * Test toString method.
     */
    @Test
    public void testToString() {
        assertEquals("[D][\u2718] description (by: 03 Apr 2020)", createDeadline().toString());
    }
}
