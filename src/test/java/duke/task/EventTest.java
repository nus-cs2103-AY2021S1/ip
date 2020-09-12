package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void newEvent_validArguments_eventObjectReturned() {
        try {
            new Event("meeting", "NUS");
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void newEvent_validArgumentsWithIsDoneSet_eventObjectReturned() {
        try {
            new Event("meeting", "NUS", true);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringForMemory_taskNotDone() {
        Event event = new Event("meeting", "NUS");
        assertEquals("[E]|0|meeting|NUS", event.toStringForMemory());
    }

    @Test
    public void toStringForMemory_taskDone() {
        Event event = new Event("meeting", "NUS", true);
        assertEquals("[E]|1|meeting|NUS", event.toStringForMemory());
    }

    @Test
    public void toStringForGui_taskNotDone() {
        Event event = new Event("meeting", "NUS");
        assertEquals("[E][\u2718] meeting (at: NUS)", event.toStringForGui());
    }

    @Test
    public void toStringForGui_taskDone() {
        Event event = new Event("meeting", "NUS", true);
        assertEquals("[E][\u2713] meeting (at: NUS)", event.toStringForGui());
    }

    @Test
    public void toStringForCli_taskNotDone() {
        Event event = new Event("meeting", "NUS");
        assertEquals("[E][✘] meeting (at: NUS)", event.toStringForCli());
    }

    @Test
    public void toStringForCli_taskDone() {
        Event event = new Event("meeting", "NUS", true);
        assertEquals("[E][✓] meeting (at: NUS)", event.toStringForCli());
    }
}
