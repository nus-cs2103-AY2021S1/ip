import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskListTest {
    
    @Test
    public void doneTask_success() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        assertTrue(taskList.doneTask(1).isDone);
    }

    @Test
    public void doneTask_taskIndexOutOfBounds_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        try {
            assertTrue(taskList.doneTask(2).isDone);
            fail();
        } catch (Exception e) {
            assertEquals(
                    "☹ OOPS!!! Please enter a valid task index to be marked as done.", 
                    e.getMessage());
        }
    }
    
    @Test
    public void deleteTask_success() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        taskList.deleteTask(1);
        assertTrue(taskList.getMyTaskList().isEmpty());
    }

    @Test
    public void deleteTask_taskIndexOutOfBounds_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.deleteTask(2);
            fail();
        } catch (Exception e) {
            assertEquals(
                    "☹ OOPS!!! Please enter a valid task index to be deleted.",
                    e.getMessage());
        }
    }
    
    @Test
    public void testStringConversion() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList taskList = new TaskList(tasks);
        assertEquals("     1.[T][✘] read book", taskList.toString());
    }
    
}
