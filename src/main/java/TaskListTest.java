import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaskListTest {
    
    @Test
    public void addTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("task1"));
        assertEquals(1, taskList.size());
    }
    
    @Test
    public void getTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("task1"));
        assertEquals("task1", taskList.getTask(1).getName());
    }

    @Test
    public void deleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("task1"));
        taskList.addTask(new Todo("task2"));
        taskList.deleteTask(1);
        assertEquals(1, taskList.size());
    }
}
