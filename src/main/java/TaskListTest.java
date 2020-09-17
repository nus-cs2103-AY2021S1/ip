/*
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskListTest {

    @Test
    public void taskList_addTask_taskAdded() {
        TaskList list = new TaskList();
        list.addTask(new TodoTask("description", false));
        assertEquals(list.getSize(), 1);
        assertEquals(list.getTask(1).toString(), "[T][✗] description");
    }

    @Test
    public void taskList_deleteTask_taskDeleted() {
        var list = new TaskList();
        list.addTask(new TodoTask("task1", false));
        list.addTask(new TodoTask("task2", false));
        list.deleteTask(1);
        assertEquals(list.getSize(), 1);
    }

    @Test
    public void taskList_addDeadlineTask_taskAdded() {
        TaskList list = new TaskList();
        list.addTask(new DeadlineTask("description", false, "2019-05-06"));
        assertEquals(list.getSize(), 1);
        assertEquals(list.getTask(1).toString(), "[D][✗] description(by: May 6 2019)");
    }

}

 */