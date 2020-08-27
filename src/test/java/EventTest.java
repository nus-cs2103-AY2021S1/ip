import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void testEventPrinting_correctFormat_success() throws DukeException {
        Task e = new Event("meeting", "2019-10-15 1200");
        assertEquals("[E][\u2718] meeting (at: Tue, 15 Oct 2019, 12:00)", e.toString());
    }

    @Test
    public void testEventPrinting_wrongFormat_exceptionThrown() {
        try {
            Task e = new Event("meeting", "Sunday");
            fail();
        } catch (DukeException ex) {
            String error = "UHOH! You need to use the proper format!\n"
                    + "eg event project meeting /at 2019-10-15 1200";
            assertEquals(error, ex.getMessage());
        }
    }

    @Test
    public void testEventPrinting_doneEvent_success() throws DukeException {
        Task e = new Event("meeting", "2019-10-15 1200");
        e.markDone();
        assertEquals("[E][\u2713] meeting (at: Tue, 15 Oct 2019, 12:00)", e.toString());
    }

    @Test
    public void testToTaskData_doneEvent_success() throws DukeException {
        Task e = new Event("meeting", "2019-10-15 1200");
        e.markDone();
        assertEquals("E ; 1 ; meeting ; 2019-10-15 1200", e.toTaskData());
    }

    @Test
    public void testToTaskData_notDoneDeadline_success() throws DukeException {
        Task e = new Event("meeting", "2019-10-15 1200");
        assertEquals("E ; 0 ; meeting ; 2019-10-15 1200", e.toTaskData());
    }
}