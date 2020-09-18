package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Tests the {@code TaskList} class.
 */
public class TaskListTest {

    /**
     * Tests addition, deletion and size of list.
     */
    @Test
    public void addAndDelete_standard_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertTrue(taskList.isEmpty());

        // Additions
        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");
        Task newTask4 = new ToDo("zest");
        Task newTask5 = new ToDo("war");
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);
        taskList.add(newTask4);
        taskList.add(newTask5);

        // Basic methods
        assertFalse(taskList.isEmpty());
        assertEquals(5, taskList.size());

        // Deletions
        taskList.delete(0);
        assertNotEquals(newTask, taskList.get(0));
        assertEquals(newTask2, taskList.get(0));
        assertNotEquals(5, taskList.size());
        assertEquals(4, taskList.size());
    }

    /**
     * Tests the get method.
     */
    @Test
    public void get_standard_success() {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");
        Task newTask4 = new ToDo("zest");
        Task newTask5 = new ToDo("war");
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);
        taskList.add(newTask4);
        taskList.add(newTask5);

        // Tests
        assertEquals(newTask, taskList.get(0));
        assertEquals(newTask3, taskList.get(2));
        assertEquals(newTask5, taskList.get(4));
    }

    /**
     * Tests the contains method.
     */
    @Test
    public void contains_doesContain_success() {
        TaskList taskList = new TaskList(new ArrayList<>());

        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);

        // Tests
        assertTrue(taskList.contains(newTask));
        assertTrue(taskList.contains(new ToDo("apple")));
        assertTrue(taskList.contains(new ToDo("test")));

        Event completedTask1 = new Event("task", true, "2-4pm", LocalDateTime.now());
        taskList.add(completedTask1);
        assertTrue(taskList.contains(new Event("task", "3-5pm")));
    }

    /**
     * Tests all methods in task list.
     */
    @Test
    public void checkIfValid_inRange_success() {
        TaskList taskList = new TaskList(new ArrayList<>());

        // Additions
        Task newTask = new ToDo("apple");
        Task newTask4 = new ToDo("zest");
        Task newTask5 = new ToDo("war");
        taskList.add(newTask);
        taskList.add(newTask4);
        taskList.add(newTask5);

        // Tests
        assertTrue(taskList.checkIfValid(1));
        assertTrue(taskList.checkIfValid(2));
    }

    /**
     * Tests all methods in task list.
     */
    @Test
    public void checkIfValid_invalidInput_success() {
        TaskList taskList = new TaskList(new ArrayList<>());

        // Additions
        Task newTask = new ToDo("apple");
        Task newTask5 = new ToDo("war");
        taskList.add(newTask);
        taskList.add(newTask5);

        // Tests
        assertFalse(taskList.checkIfValid(0));
        assertFalse(taskList.checkIfValid(-3));
        assertFalse(taskList.checkIfValid(3));
    }

    // TODO: Match all, sort, 2 constructors

    /**
     * Tests all methods in task list.
     */
    @Test
    public void testList() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertTrue(taskList.isEmpty());

        // Additions
        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");
        Task newTask4 = new ToDo("zest");
        Task newTask5 = new ToDo("war");
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);
        taskList.add(newTask4);
        taskList.add(newTask5);

        // Basic methods
        assertFalse(taskList.isEmpty());
        assertEquals(5, taskList.size());
        assertSame(newTask, taskList.get(0));
        assertTrue(taskList.checkIfValid(5));
        assertFalse(taskList.checkIfValid(10));
        assertFalse(taskList.checkIfValid(-2));

        // On match
        TaskList newList = taskList.matchAll("ple");
        assertSame(newTask, newList.get(0));
        assertSame(newTask2, newList.get(1));

        // Deletions
        taskList.delete(0);
        assertNotSame(newTask, taskList.get(0));
        assertSame(newTask2, taskList.get(0));
        assertNotEquals(5, taskList.size());
        assertEquals(4, taskList.size());
    }
}
