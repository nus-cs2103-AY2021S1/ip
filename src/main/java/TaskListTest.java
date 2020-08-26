import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
