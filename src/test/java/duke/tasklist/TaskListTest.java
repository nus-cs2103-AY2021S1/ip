package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;

public class TaskListTest {

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
