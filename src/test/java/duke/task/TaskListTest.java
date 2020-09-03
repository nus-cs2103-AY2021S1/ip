package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_taskListWithItems_taskAdded() {
        ArrayList<Task> testInputs = new ArrayList<>();
        testInputs.add(new ToDo("todo walk dog"));
        TaskList test = new TaskList(testInputs);
        test.add(new ToDo("todo test methods"));
        assertEquals(2, test.size());
    }

    @Test
    public void addTask_taskListWithoutItems_taskAdded() {
        ArrayList<Task> testInputs = new ArrayList<>();
        TaskList test = new TaskList(testInputs);
        test.add(new ToDo("todo test methods"));
        assertEquals(1, test.size());
    }

    @Test
    public void deleteTask_taskListWithItems_taskDeleted() {
        ArrayList<Task> testInputs = new ArrayList<>();
        testInputs.add(new ToDo("todo walk dog"));
        TaskList test = new TaskList(testInputs);
        test.add(new ToDo("todo test methods"));
        test.delete(1);
        assertEquals(1, test.size());
    }

    @Test
    public void deleteTask_taskListWithoutItems_exceptionThrown() {
        ArrayList<Task> testInputs = new ArrayList<>();
        TaskList test = new TaskList(testInputs);
        try {
            test.delete(1);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Index 1 out of bounds"));
        }
    }

    @Test
    public void getSize_taskListWithOneItem_one() {
        ArrayList<Task> testInputs = new ArrayList<>();
        TaskList test = new TaskList(testInputs);
        test.add(new ToDo("todo test methods"));
        assertEquals(1, test.size());
    }

    @Test
    public void getSize_taskListWithoutItems_zero() {
        ArrayList<Task> testInputs = new ArrayList<>();
        TaskList test = new TaskList(testInputs);
        assertEquals(0, test.size());

    }

    @Test
    public void getItem_taskListWithoutItems_exceptionThrown() {
        ArrayList<Task> testInputs = new ArrayList<>();
        TaskList test = new TaskList(testInputs);
        try {
            test.get(1);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Index 1 out of bounds"));
        }
    }

    @Test
    public void getItem_taskListWithItems_itemRetrieved() {
        ArrayList<Task> testInputs = new ArrayList<>();
        testInputs.add(new ToDo("todo walk dog"));
        TaskList test = new TaskList(testInputs);
        test.add(new ToDo("todo test methods"));
        assertTrue(test.get(1) instanceof ToDo);
    }
}
