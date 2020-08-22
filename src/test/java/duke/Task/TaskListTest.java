package duke.Task;

import duke.Exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void init() {
        taskList = new TaskList();
    }

    void addTask() {
        taskList.add(new TaskTestStub("Test"));
    }

    @Test
    void getTasks() {
        assertEquals(new ArrayList<Task>(), taskList.getTasks());
    }

    @Test
    void getTaskId_validTaskId() throws DukeException {
        addTask();
        assertEquals("[✘] Test", taskList.get(1).toString());
    }

    @Test
    void getTaskId_invalidTaskId_exceptionThrown() {
        try {
            taskList.get(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task No.1 is not in your list. "
                    + "Please enter a valid task ID.", e.getMessage());
        }
    }

    @Test
    void addTask_sizeIncrease() {
        assertEquals(0, taskList.size());
        addTask();
        assertEquals(1, taskList.size());
    }

    @Test
    void removeTask_sizeDecrease() throws DukeException {
        addTask();
        assertEquals(1, taskList.size());
        taskList.remove(1);
        assertEquals(0, taskList.size());
    }

    @Test
    void getSize() {
        assertEquals(0, taskList.size());
        addTask();
        assertEquals(1, taskList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(taskList.isEmpty());
        addTask();
        assertFalse(taskList.isEmpty());
    }


}
