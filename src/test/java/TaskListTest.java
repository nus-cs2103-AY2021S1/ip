import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskListTest {
    
    @Test
    public void doneTask_success() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        tasks.add(new Todo("read book"));
        taskIndexes.add(1);
        TaskList taskList = new TaskList(tasks);
        assertTrue(taskList.doneTasks(taskIndexes).get(0).isDone);
    }

    @Test
    public void doneTask_taskIndexOutOfBounds_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        tasks.add(new Todo("read book"));
        taskIndexes.add(2);
        TaskList taskList = new TaskList(tasks);
        try {
            assertTrue(taskList.doneTasks(taskIndexes).get(1).isDone);
            fail();
        } catch (Exception e) {
            assertEquals(
                    "OOPS!!! Please enter a valid task index or task indexes to be marked as done.", 
                    e.getMessage());
        }
    }
    
    @Test
    public void deleteTask_success() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        tasks.add(new Todo("read book"));
        taskIndexes.add(1);
        TaskList taskList = new TaskList(tasks);
        taskList.deleteTasks(taskIndexes);
        assertTrue(taskList.getMyTaskList().isEmpty());
    }

    @Test
    public void deleteTask_taskIndexOutOfBounds_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        tasks.add(new Todo("read book"));
        taskIndexes.add(2);
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.deleteTasks(taskIndexes);
            fail();
        } catch (Exception e) {
            assertEquals(
                    "OOPS!!! Please enter a valid task index or task indexes todo to be deleted.",
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
