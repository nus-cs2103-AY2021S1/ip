package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class TaskListTest {

    @Test
    public void add_task_correctOutput() {
        Task t = new TodoTask("test");
        TaskList taskList = new TaskList();
        taskList.add(t);
        assertEquals("[T][\u2718] test", taskList.getListRepr().get(0));
    }

    @Test
    public void markTaskAsDone_taskNum_correctOutput() {
        try {
            Task t = new TodoTask("test");
            TaskList taskList = new TaskList();
            taskList.add(t);
            t = taskList.markTaskAsDone(1);
            assertEquals("[T][\u2713] test", taskList.getListRepr().get(0));
            assertEquals("[T][\u2713] test", t.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void delete_taskNum_correctOutput() {
        Task t = new TodoTask("test");
        TaskList taskList = new TaskList();
        taskList.add(t);
        assertEquals(1, taskList.getListRepr().size());
        t = taskList.delete(1);
        assertEquals(0, taskList.getListRepr().size());
        assertEquals("[T][\u2718] test", t.toString());
    }

    @Test
    public void getListRepr_taskList_correctOutput() {
        try {
            Task t = new TodoTask("test");
            Task t1 = new DeadlineTask("test", "2020-08-22");
            Task t2 = new EventTask("test", "2020-08-22");
            TaskList taskList = new TaskList();
            taskList.add(t);
            taskList.add(t1);
            taskList.add(t2);
            assertEquals(t.toString(), taskList.getListRepr().get(0));
            assertEquals(t1.toString(), taskList.getListRepr().get(1));
            assertEquals(t2.toString(), taskList.getListRepr().get(2));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getListStatus_taskList_correctOutput() {
        try {
            Task t = new TodoTask("test");
            Task t1 = new DeadlineTask("test", "2020-08-22");
            TaskList taskList = new TaskList();
            taskList.add(t);
            assertEquals("There is now 1 task in your list!", taskList.getListStatus());
            taskList.add(t1);
            assertEquals("There are now 2 tasks in your list!", taskList.getListStatus());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getData_taskList_correctOutput() {
        try {
            Task t = new TodoTask("test");
            Task t1 = new DeadlineTask("test", "2020-08-22");
            Task t2 = new EventTask("test", "2020-08-22");
            TaskList taskList = new TaskList();
            taskList.add(t);
            taskList.add(t1);
            taskList.add(t2);
            assertEquals(t.getData(), taskList.getData().get(0));
            assertEquals(t1.getData(), taskList.getData().get(1));
            assertEquals(t2.getData(), taskList.getData().get(2));
        } catch (DukeException e) {
            fail();
        }
    }
}
