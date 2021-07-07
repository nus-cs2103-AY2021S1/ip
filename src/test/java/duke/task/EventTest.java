package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.InvalidSaveException;

/**
 * JUnit test class for testing the Event class
 */
public class EventTest {

    /**
     * Tests to see if the Event created with valid timing strings will be successful.
     */
    @Test
    public void createEvent_validStrings_success() throws DukeException {
        String input1 = "meeting /at 21-09-2020 0800-1000";
        String input2 = "meeting /at 21-09-2020 0800-21-09-2020 1000";
        String input3 = "meeting /at 21-09-2020 0800-23-09-2020 1000";
        String output1 = "[E][\u2718] meeting (at: 21-09-2020 0800-1000)";
        String output2 = "[E][\u2718] meeting (at: 21-09-2020 0800-1000)";
        String output3 = "[E][\u2713] meeting (at: 21-09-2020 0800-23-09-2020 1000)";
        Event event3 = new Event(input3);
        event3.markDone();
        assertEquals(output1, new Event(input1).toString());
        assertEquals(output2, new Event(input2).toString());
        assertEquals(output3, event3.toString());
    }

    /**
     * Tests to see if the Event correctly rejects invalid String inputs.
     */
    @Test
    public void createEvent_invalidStrings_errorThrown() {
        try {
            String input1 = "meeting /at 21-09-2020 0800-20-09-2020 0800"; //End timing earlier than start time
            new Event(input1);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("End timing must be later than start timing!"));
        }
        try {
            String input2 = "meeting /at abcde";
            new Event(input2);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid event timing. Please use either"));
        }
        try {
            String input3 = "meeting /at 21-09-2020 2020- 21-09-2020 20:30";
            new Event(input3);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("is not a valid event timing. Please use either"));
        }
    }

    /**
     * Tests 1. if the summary comes out in the form required and
     * 2. The same Event can be recreated from the summary.
     */
    @Test
    public void testSummary_standardEvents_success() throws DukeException, InvalidSaveException {
        String input1 = "meeting /at 21-09-2020 0800 21-09-2020 1000";
        String input2 = "carnival@park /at 23-10-2020 0800 05-11-2020 2300";
        Event event1 = new Event(input1);
        event1.markDone();
        Event event2 = new Event(input2);
        String summary1 = event1.getSummary();
        String summary2 = event2.getSummary();
        String expectedSummary1 = "E|1|meeting|21-09-2020 0800-1000";
        String expectedSummary2 = "E|0|carnival@park|23-10-2020 0800-05-11-2020 2300";
        assertEquals(expectedSummary1, summary1);
        assertEquals(expectedSummary2, summary2);
        assertEquals(event1.toString(), Event.reconstructFromSummary(summary1).toString());
        assertEquals(event2.toString(), Event.reconstructFromSummary(summary2).toString());

    }




}
