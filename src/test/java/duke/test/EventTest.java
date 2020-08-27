package duke.test;


import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    private static final char DONE = '\u2713';
    private static final char NOT_DONE = '\u2717';

    // @Test
    // public void dummyTest() {
    //     assertEquals(2, 2);
    // }


    @Test
    public void Event_NotDone_toString_printedCorrectly() {
        Event testEvent = new Event("stuff /at 2020-01-01");
        String expected = String.format("[E][%c] %s (at: %s)", NOT_DONE, "stuff", "01 Jan 2020");
        assertEquals(expected, testEvent.toString());
    }


    @Test
    public void Event_Done_toString_printedCorrectly() {
        Event testEvent = new Event("stuff /at 2020-01-01", true);
        String expected = String.format("[E][%c] %s (at: %s)", DONE, "stuff", "01 Jan 2020");
        assertEquals(expected, testEvent.toString());
    }

}
