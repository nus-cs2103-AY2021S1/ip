package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.logic.TaskList;
import duke.task.DukeTask;
import stub.DukeTaskStub;

public class TaskListTest {

    @Test
    public void testAddToList() {
        DukeTask task = new DukeTaskStub();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(task);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testGetList() {
        DukeTask task = new DukeTaskStub();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(task);

        ArrayList<DukeTask> expected = new ArrayList<>();
        expected.add(task);

        assertEquals(expected, taskList.getTaskList());
    }

    @Test
    public void testMarkDone() {
        DukeTask task = new DukeTaskStub();
        assertFalse(task.getDoneStatus());

        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(task);
        taskList.markDone(0);

        assertTrue(task.getDoneStatus());
    }

    @Test
    public void testDeleteFromList() {
        DukeTask task = new DukeTaskStub();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(task);
        assertEquals(task, taskList.deleteFromList(0));
    }
}
