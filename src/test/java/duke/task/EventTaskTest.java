package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTaskTest {
    @Test
    public void createEventTask_incorrectDateInput_throwDukeException() {
        try {
            EventTask task = new EventTask("test", "2020/08/22");
            fail();
        } catch (DukeException e) {
            assertEquals("Date format should be YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    public void getData_eventTaskNotDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22");
            String[] str = task.getData().split("\\|");
            assertEquals(str[0], "E");
            assertEquals(str[1], "0");
            assertEquals(str[2], "test");
            assertEquals(str[3], "2020-08-22");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getData_eventTaskDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22").markAsDone();
            String[] str = task.getData().split("\\|");
            assertEquals(str[0], "E");
            assertEquals(str[1], "1");
            assertEquals(str[2], "test");
            assertEquals(str[3], "2020-08-22");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_eventTaskNotDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22");
            String str = task.toString();
            assertEquals(str, "[E][\u2718] test (at: Aug 22 2020)");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_eventTaskDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22").markAsDone();
            String str = task.toString();
            assertEquals(str, "[E][\u2713] test (at: Aug 22 2020)");
        } catch (DukeException e) {
            fail();
        }
    }
}