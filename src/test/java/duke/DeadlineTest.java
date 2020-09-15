package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Class that encapsulates tests for the Deadline class.
 */
public class DeadlineTest {
    /**
     * Tests the toString() method for Deadline.
     */
    @Test
    public void toString_test_formattedString() {
        Task t = new Deadline("test", true, LocalDate.parse("2020-03-19"));
        assertEquals("[D][✓] test (by: Mar 19 2020)", t.toString());
    }
}
