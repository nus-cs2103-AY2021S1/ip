import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.logic.tasks.Event;
import duke.logic.tasks.Task;

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
            String error = "UHOH! Y'all need to learn to use the proper format!\n"
                    + "Try somethin' like this: \nevent karate competition /at 2019-10-15 1200";
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
