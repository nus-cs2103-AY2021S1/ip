package logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.logic.TaskList;
import duke.task.DukeTask;
import stub.DukeTaskStub;


public class TaskListTest {
    @Test
    public void taskListTest() {
        DukeTask task = new DukeTaskStub("TESTING");
        ArrayList<DukeTask> arrayList = new ArrayList<>();
        TaskList taskList = new TaskList(arrayList);
        assertEquals(taskList.getTaskList(), arrayList);

        assertEquals(taskList.getSize(), 0);
        taskList.addToList(task);
        assertEquals(taskList.getSize(), 1);
        taskList.deleteFromList(0);
        assertEquals(taskList.getSize(), 0);
        taskList.addToList(task);
        taskList.markDone(0);
        assertTrue(taskList.deleteFromList(0).getDoneStatus());
    }
}
