package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
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

    @Test
    public void constructor_noParams_success() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void constructor_withArrayList_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("asd"));
        TaskList taskList = new TaskList(tasks);
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void sort_standard_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Event event1 = new Event("apple", "2pm");
        Event event2 = new Event("apple", true, "2pm", LocalDateTime.now());
        Deadline deadline1 = new Deadline("apple", LocalDateTime.now().minusDays(5));
        Deadline deadline2 = new Deadline("test", LocalDateTime.now().minusDays(1));
        ToDo toDo = new ToDo("test");

        // Add tasks
        taskList.add(event1);
        taskList.add(event2);
        taskList.add(deadline1);
        taskList.add(deadline2);
        taskList.add(toDo);

        // Sort list
        taskList.sort();

        // After sort
        assertEquals(deadline1, taskList.get(0));
        assertEquals(deadline2, taskList.get(1));
        assertEquals(event1, taskList.get(2));
        assertEquals(toDo, taskList.get(3));
        assertEquals(event2, taskList.get(4));
    }

    @Test
    public void matchAll_found_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");

        // Add tasks
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);

        // On match
        TaskList newList = taskList.matchAll("ple");
        assertSame(newTask, newList.get(0));
        assertSame(newTask2, newList.get(1));
    }

    @Test
    public void matchAll_noMatch_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task newTask = new ToDo("apple");
        Task newTask2 = new ToDo("apple and orange");
        Task newTask3 = new ToDo("test");

        // Add tasks
        taskList.add(newTask);
        taskList.add(newTask2);
        taskList.add(newTask3);

        // On match
        TaskList newList = taskList.matchAll("pLe");
        assertTrue(newList.isEmpty());
    }

}
