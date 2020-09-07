package duke.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskStub;

public class TaskListTest {
    private static final TaskStub TASK_ONE = new TaskStub("task 1", false);
    private static final TaskStub TASK_TWO = new TaskStub("task 2", false);
    private static final TaskStub TASK_TWO_COMPLETE = new TaskStub("task 2", true);
    private static final TaskStub TASK_THREE = new TaskStub("task 3", true);

    @Test
    public void testTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getCurrCapacity(), 0);

        assertEquals(taskList.add(TASK_ONE), TASK_ONE);
        assertEquals(taskList.getCurrCapacity(), 1);

        assertEquals(taskList.add(TASK_TWO), TASK_TWO);
        assertEquals(taskList.getCurrCapacity(), 2);

        assertEquals(taskList.completeTask(2), TASK_TWO_COMPLETE);
        assertEquals(taskList.getCurrCapacity(), 2);

        assertEquals(taskList.add(TASK_THREE), TASK_THREE);
        assertEquals(taskList.getCurrCapacity(), 3);

        assertEquals(taskList.remove(1), TASK_ONE);
        assertEquals(taskList.getCurrCapacity(), 2);
    }
}
