package duke.task;

import duke.exceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    public void completeTask_invalidTaskNumber_throwException() {
        TaskList testList = new TaskList();
        assertThrows(NoSuchTaskException.class, () -> testList.completeTask(55));
    }

    @Test
    public void deleteTask_invalidTaskNumber_throwException() {
        TaskList testList = new TaskList();
        assertThrows(NoSuchTaskException.class, () -> testList.completeTask(123));
    }
}
