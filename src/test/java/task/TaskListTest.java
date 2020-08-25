package task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    public static final Task TASK = new Task("Test");
    public static final Task TASK2 = new Task("Meh2");
    public static final Task TASK3 = new Task("GG3");

    @Test
    public void testToString() {
        List<Task> list = new ArrayList<>();
        list.add(TASK);
        list.add(TASK2);
        list.add(TASK3);
        TaskList tl = new TaskList(list);
        assertEquals("[✘] Test",tl.getTask(0).toString());
        assertEquals("[✘] Meh2",tl.getTask(1).toString());
        assertEquals("[✘] GG3",tl.getTask(2).toString());
    }

    @Test
    public void testSize() {
        List<Task> list = new ArrayList<>();
        list.add(TASK);
        TaskList tl = new TaskList(list);
        assertEquals(1,tl.size());
        list.add(TASK2);
        assertEquals(2,tl.size());
        list.add(TASK3);
        assertEquals(3,tl.size());
    }

    @Test
    public void testGetTask() {
        List<Task> list = new ArrayList<>();
        list.add(TASK);
        list.add(TASK2);
        list.add(TASK3);
        TaskList tl = new TaskList(list);
        assertEquals(TASK, tl.getTask(0));
        assertEquals(TASK2,tl.getTask(1));
        assertEquals(TASK3,tl.getTask(2));
    }

    @Test
    public void testAdding() {
        TaskList tl = new TaskList();
        tl.addToTaskList(TaskListTest.TASK);
        tl.addToTaskList(TaskListTest.TASK2);
        tl.addToTaskList(TaskListTest.TASK3);
        assertEquals(TASK,tl.getTask(0));
        assertEquals(TASK2,tl.getTask(1));
        assertEquals(TASK3,tl.getTask(2));
    }

    @Test
    public void testDeleting() {
        TaskList tl = new TaskList();
        tl.addToTaskList(TaskListTest.TASK);
        tl.addToTaskList(TaskListTest.TASK2);
        tl.addToTaskList(TaskListTest.TASK3);

        assertEquals(TASK,tl.getTask(0));
        assertEquals(TASK2,tl.getTask(1));
        assertEquals(TASK3,tl.getTask(2));

        tl.deleteFromTaskList(2);

        try {
            assertEquals(TASK3, tl.getTask(2));
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 2 out of bounds for length 2", e.getMessage());
        }
    }
}
