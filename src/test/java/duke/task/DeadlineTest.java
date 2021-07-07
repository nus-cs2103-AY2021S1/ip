package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.InvalidSaveException;

/**
 * JUnit test class to test the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests to see if the Deadline created with valid timing strings will be successful.
     */
    @Test
    public void createDeadline_validStrings_success() throws DukeException {
        String input1 = "meeting /by 21-09-2020 0800";
        String input2 = "meeting /by 23-09-2020 1000";
        String output1 = "[D][\u2718] meeting (by: 21-09-2020 0800)";
        String output2 = "[D][\u2713] meeting (by: 23-09-2020 1000)";
        Deadline deadline2 = new Deadline(input2);
        deadline2.markDone();
        assertEquals(output1, new Deadline(input1).toString());
        assertEquals(output2, deadline2.toString());
    }

    /**
     * Tests to see if the Deadline class correctly rejects invalid String inputs.
     */
    @Test
    public void createDeadline_invalidStrings_errorThrown() {
        try {
            String input1 = "meeting /by abcde";
            new Deadline(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains(" is an invalid datetime format! Please use "));
        }
        try {
            String input2 = "meeting";
            new Deadline(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Please follow the format "));
        }
    }

    /**
     * Tests 1. if the summary comes out in the form required and
     * 2. The same Deadline can be recreated from the summary.
     */
    @Test
    public void testSummary_standardDeadlines_success() throws InvalidSaveException, DukeException {
        String input1 = "meeting /by 21-09-2020 0800";
        String input2 = "carnival@park /by 23-10-2020 0800";
        Deadline event1 = new Deadline(input1);
        event1.markDone();
        Deadline event2 = new Deadline(input2);
        String summary1 = event1.getSummary();
        String summary2 = event2.getSummary();
        String expectedSummary1 = "D|1|meeting|21-09-2020 0800";
        String expectedSummary2 = "D|0|carnival@park|23-10-2020 0800";
        assertEquals(expectedSummary1, summary1);
        assertEquals(expectedSummary2, summary2);
        assertEquals(event1.toString(), Deadline.reconstructFromSummary(summary1).toString());
        assertEquals(event2.toString(), Deadline.reconstructFromSummary(summary2).toString());

    }

}
