import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Task;

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
            String error = "UHOH! Y'all need to learn to use the proper format!\n"
                    + "Try somethin' like this: \ndeadline build spaceship /by 2019-10-15 2359";
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
