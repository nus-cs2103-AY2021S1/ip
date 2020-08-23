package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

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
            assertEquals("E", str[0]);
            assertEquals("0", str[1]);
            assertEquals("test", str[2]);
            assertEquals("2020-08-22", str[3]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getData_eventTaskDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22").markAsDone();
            String[] str = task.getData().split("\\|");
            assertEquals("E", str[0]);
            assertEquals("1", str[1]);
            assertEquals("test", str[2]);
            assertEquals("2020-08-22", str[3]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_eventTaskNotDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22");
            String str = task.toString();
            assertEquals("[E][\u2718] test (at: Aug 22 2020)", str);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toString_eventTaskDone_correctOutput() {
        try {
            EventTask task = new EventTask("test", "2020-08-22").markAsDone();
            String str = task.toString();
            assertEquals("[E][\u2713] test (at: Aug 22 2020)", str);
        } catch (DukeException e) {
            fail();
        }
    }
}