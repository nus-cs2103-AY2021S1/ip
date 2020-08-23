package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void createTask_withoutTime_Success() throws DukeException {
        Deadline d = Deadline.createTask("DeAdLine project /by 2019/02/20");
        assertEquals("[D][✘] project (FINISH by: 20 February 2019)", d.toString());
    }

    @Test
    public void createTask_withTime_Success() throws DukeException {
        Deadline d = Deadline.createTask("DeAdLine tutorial /by 2019/02/20 12:00");
        assertEquals("[D][✘] tutorial (FINISH by: 20 February 2019 12:00)", d.toString());
    }

    @Test
    public void createTask_missingDescription_Failure() {

        try {
            Deadline.createTask("deadLine  /by 2019/02/20 12:00");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " Oops!! You gonna forget what this is about if you\n"
                    + " dont give me a description... *woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_missingDateTime_Failure() {

        try {
            Deadline.createTask("deadLine  project /by ");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " Oops!! You did not state when you wanna finish this by...\n"
                    + " Are you planning to procrastinate? *woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_keyWord_Failure() {

        try {
            Deadline.createTask("deadLine  project by 2019/02/20 16:00");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " Oops!! You missed out some vital information/keyword... *woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void createTask_wrongDateTimeFormat_Failure() {

        try {
            Deadline.createTask("DeAdLine project /by 20/02/2019 1800");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " Please input deadline in following format:\n"
                    + "   YYYY/MM/DD HH:MM!\n" + " *Woof woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }

    }

    @Test
    public void task_checkFormatPrint() throws DukeException {
        Deadline d = Deadline.createTask("deadLine        project      /by  2019/02/20");
        assertEquals("[D][✘] project (FINISH by: 20 February 2019)", d.toString());
    }

    @Test
    public void createTaskWithDoneTest() {
        Deadline d = new Deadline("project", "20 February 2019", true);
        assertEquals("[D][✓] project (FINISH by: 20 February 2019)", d.toString());
    }

    @Test
    public void compareDateTest_sameDate() {
        Deadline d = new Deadline("project", "20 February 2019", true);
        assertTrue(d.compareDate(LocalDate.parse("2019-02-20")));
    }

    @Test
    public void compareDateTest_diffDate() throws DukeException {
        Deadline d = Deadline.createTask("deadLine        project      /by  2019/02/20");
        assertFalse(d.compareDate(LocalDate.parse("2019-02-21")));
    }

}

