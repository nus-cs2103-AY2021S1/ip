package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals(str[0], "D");
            assertEquals(str[1], "0");
            assertEquals(str[2], "test");
            assertEquals(str[3], "2020-08-22");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getData_deadlineTaskDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22").markAsDone();
            String[] str = task.getData().split("\\|");
            assertEquals(str[0], "D");
            assertEquals(str[1], "1");
            assertEquals(str[2], "test");
            assertEquals(str[3], "2020-08-22");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_deadlineTaskNotDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22");
            String str = task.toString();
            assertEquals(str, "[D][\u2718] test (by: Aug 22 2020)");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_deadlineTaskDone_correctOutput() {
        try {
            DeadlineTask task = new DeadlineTask("test", "2020-08-22").markAsDone();
            String str = task.toString();
            assertEquals(str, "[D][\u2713] test (by: Aug 22 2020)");
        } catch (DukeException e) {
            fail();
        }
    }
}