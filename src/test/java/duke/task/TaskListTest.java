package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.NoSuchTaskException;

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
