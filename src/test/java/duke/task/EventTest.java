package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void initialiseNewEvent_notDone_pass() {
        Event testEvent = new Event("0", "training",
                "2020-08-31 6pm");
        String expectedOutput = "[E][✗] training (at: Aug 31 2020 6pm)";
        assertEquals(expectedOutput, testEvent.toString());
    }

    @Test
    public void initialiseNewEvent_done_pass() {
        Event testEvent = new Event("1", "training",
                "2020-08-31 6pm");
        String expectedOutput = "[E][✓] training (at: Aug 31 2020 6pm)";
        assertEquals(expectedOutput, testEvent.toString());
    }
}
