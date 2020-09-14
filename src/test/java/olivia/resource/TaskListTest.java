package olivia.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import olivia.task.ToDo;


public class TaskListTest {

    @Test
    public void taskList_addTask_taskAdded() {
        var list = new TaskList();
        list.addTask(new ToDo("placeholder", false));
        assertEquals(list.size(), 1);
        assertEquals(list.getTask(1).toString(), "[T][\u2718] placeholder");
    }

    @Test
    public void taskList_completeTask_taskCompleted() {
        var list = new TaskList();
        list.addTask(new ToDo("placeholder", false));
        list.completeTask(1);
        assertEquals(list.getTask(1).toString(), "[T][\u2713] placeholder");
    }

    @Test
    public void taskList_deleteTask_taskDeleted() {
        var list = new TaskList();
        list.addTask(new ToDo("placeholder", false));
        list.deleteTask(1);
        assertEquals(list.size(), 0);
    }

}
