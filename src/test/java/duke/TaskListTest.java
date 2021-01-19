package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

class TaskListTest {

    private Task testTask1 = new ToDo("test");
    private Task testTask2 = new Deadline("test", "test");
    private Task testTask3 = new Event("test", "test");
    private Task testTask4 = new Task("test");

    TaskList makeTestList() {
        TaskList tasks = new TaskList();
        tasks.addTask(testTask1);
        tasks.addTask(testTask2);
        tasks.addTask(testTask3);
        tasks.addTask(testTask4);
        return tasks;
    }

    @Test
    void numTasks_emptyList_zero() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.numTasks());
    }

    @Test
    void numTasks_nonemptyList_sizeOfList() {
        assertEquals(4, makeTestList().numTasks());
    }

    @Test
    void addTask_task_success() {
        String testPhrase = "this is a test";
        Task testTask = new Task(testPhrase);
        TaskList testTaskList = makeTestList();
        testTaskList.addTask(testTask);

        assertEquals(testTask, testTaskList.getTask(testTaskList.numTasks() - 1));
    }

    @Test
    void getTask_firstItem_success() {
        assertEquals(testTask1, makeTestList().getTask(0));
    }

    @Test
    void getTask_lastItem_success() {
        assertEquals(testTask4, makeTestList().getTask(makeTestList().numTasks() - 1));
    }

    @Test
    void getTask_outOfBound_indexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> makeTestList().getTask(makeTestList().numTasks()));
    }

    @Test
    void removeTask_firstItem_success() {
        TaskList testTaskList = makeTestList();
        assertEquals(testTask1, testTaskList.removeTask(0));
        assertEquals(testTask2, testTaskList.getTask(0));
        assertEquals(testTask3, testTaskList.getTask(1));
        assertEquals(testTask4, testTaskList.getTask(2));
    }

    @Test
    void removeTask_lastItem_success() {
        TaskList testTaskList = makeTestList();
        assertEquals(testTask4, testTaskList.removeTask(testTaskList.numTasks() - 1));
        assertEquals(testTask3, makeTestList().getTask(testTaskList.numTasks() - 1));
    }

    @Test
    void removeTask_outOfBound_indexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> makeTestList().removeTask(makeTestList().numTasks()));
    }
}
