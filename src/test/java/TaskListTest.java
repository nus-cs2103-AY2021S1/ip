import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetTaskLength(){
        assertEquals(0, new TaskList().getTaskLength());
        List<Task> taskListTest = new ArrayList<>();
        taskListTest.add(new Todo("a"));
        taskListTest.add(new Todo("b"));
        taskListTest.add(new Todo("c"));
        assertEquals(3, taskListTest.size());
    }

    @Test
    public void testGetTask() {
        TaskList taskListTest = new TaskList();
        Todo todoA = new Todo("a");
        Todo todoB = new Todo("b");
        taskListTest.addTask(todoA);
        taskListTest.addTask(todoB);
        assertEquals(todoA, taskListTest.getTask(1));
    }

    @Test
    public void testAddTask() {
        TaskList taskListTest = new TaskList();
        Todo todoA = new Todo("a");
        Todo todoB = new Todo("b");
        taskListTest.addTask(todoA);
        taskListTest.addTask(todoB);
        assertEquals(2, taskListTest.getTaskLength());
    }

}