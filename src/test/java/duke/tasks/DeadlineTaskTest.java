package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTaskTest {

    @Test
    public void createDeadlineTask_incorrectDateInput_throwDukeException() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020/08/22");
            fail();
        } catch (DukeException e) {
            assertEquals("Date format should be YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    public void getData_deadlineTaskNotDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22");
            String[] str = task.getData().split("\\|");
            assertEquals("D", str[0]);
            assertEquals("0", str[1]);
            assertEquals("test", str[2]);
            assertEquals("2020-08-22", str[3]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getData_deadlineTaskDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22").markAsDone();
            String[] str = task.getData().split("\\|");
            assertEquals("D", str[0]);
            assertEquals("1", str[1]);
            assertEquals("test", str[2]);
            assertEquals("2020-08-22", str[3]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_deadlineTaskNotDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22");
            String str = task.toString();
            assertEquals("[D][\u2718] test (by: Aug 22 2020)", str);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_deadlineTaskDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22").markAsDone();
            String str = task.toString();
            assertEquals("[D][\u2713] test (by: Aug 22 2020)", str);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void contains_correctKeyword_true() {
        try {
            assertTrue(new DeadlineTask("test", "2020-08-22").contains(new String[]{"test"}));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void contains_wrongKeyword_false() {
        try {
            assertFalse(new DeadlineTask("test", "2020-08-22").contains(new String[]{"wrong"}));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void update_wrongDateFormat_throwDukeException() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("test", "2020-09-05");
            deadlineTask.update("testing /by 20-09-4");
            fail();
        } catch (DukeException e) {
            assertEquals("Date format should be YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    public void update_correctInput_correctOutput() {
        try {
            DeadlineTask deadlineTask = new DeadlineTask("test", "2020-09-05");
            deadlineTask = deadlineTask.update("testing /by 2020-09-04");
            assertEquals("[D][\u2718] testing (by: Sep 4 2020)", deadlineTask.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
