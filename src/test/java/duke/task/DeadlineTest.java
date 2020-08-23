package duke.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline createDeadline() {
        return new Deadline("description", LocalDate.parse("2020-04-03"));
    }

    @Test
    public void testIsDateEquals() {
        assertTrue(createDeadline().isDateEqual(LocalDate.parse("2020-04-03")));
        assertFalse(createDeadline().isDateEqual(LocalDate.parse("2020-04-08")));
    }

    @Test
    public void testGetData() {
        assertEquals("D_0_description_2020-04-03", createDeadline().getData().trim());
    }

    @Test
    public void testToString() {
        assertEquals("[D][\u2713] description (by: 03 Apr 2020)", createDeadline().toString());
    }
}
