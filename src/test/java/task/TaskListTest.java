package task;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void parseTaskFromStringTest() {
        try {
            Task ret = TaskList.parseTaskFromString("[D][✓] hello (by: Apr 20 2020, 12:00:59 PM)");
            Task actual = new Deadline("hello", "2020-04-20T12:00:59");
            actual.setDone(true);
            assertEquals(ret.toString(), actual.toString());
        } catch (DukeException e) {
            fail("Exception thrown! " + e);
        }
    }

}
