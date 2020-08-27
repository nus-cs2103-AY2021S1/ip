import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void testDeadlinePrinting_correctFormat_success() throws DukeException {
        Task d = new Deadline("proj", "2019-10-15 2359");
        assertEquals("[D][\u2718] proj (by: Tue, 15 Oct 2019, 23:59)", d.toString());
    }

    @Test
    public void testDeadlinePrinting_wrongFormat_exceptionThrown() {
        try {
            Task d = new Deadline("project", "Sunday");
            fail();
        } catch (DukeException ex) {
            String error = "UHOH! You need to use the proper format!\n"
                    + "eg deadline return book /by 2019-10-15 2359";
            assertEquals(error, ex.getMessage());
        }
    }

    @Test
    public void testDeadlinePrinting_doneDeadline_success() throws DukeException {
        Task d = new Deadline("proj", "2019-10-15 2359");
        d.markDone();
        assertEquals("[D][\u2713] proj (by: Tue, 15 Oct 2019, 23:59)", d.toString());
    }

    @Test
    public void testToTaskData_doneDeadline_success() throws DukeException {
        Task d = new Deadline("proj", "2019-10-15 2359");
        d.markDone();
        assertEquals("D ; 1 ; proj ; 2019-10-15 2359", d.toTaskData());
    }

    @Test
    public void testToTaskData_notDoneDeadline_success() throws DukeException {
        Task d = new Deadline("proj", "2019-10-15 2359");
        assertEquals("D ; 0 ; proj ; 2019-10-15 2359", d.toTaskData());
    }

}
