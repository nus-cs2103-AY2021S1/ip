package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Tests the {@code Ui} class to ensure output to user matches.
 */
public class UiTest {

    private final Ui ui = new Ui();
    private final ToDo toDo = new ToDo("test");

    /**
     * Tests goodbye method.
     */
    @Test
    public void testGoodbye() {
        assertEquals("Bye! Hope to see you again soon!\n", ui.goodbye());
    }

    /**
     * Tests markTaskAsDone method.
     */
    @Test
    public void testMarkTaskAsDone() {
        toDo.markAsDone();
        assertEquals("Incredible! I've marked this task as done:\n    [T][\u2713] test\n", ui.markTaskAsDone(toDo));
    }

    /**
     * Tests deleteTask method.
     */
    @Test
    public void testDeleteTask() {
        assertEquals("Noted. I've removed this task:\n    [T][\u2718] test\n"
            + "Now you have 5 tasks in the list.\n", ui.deleteTask(toDo, 5));
    }

    /**
     * Tests addTask method.
     */
    @Test
    public void testAddTask() {
        assertEquals("Got it. I've added this task:\n    [T][\u2718] test\n"
            + "Now you have 4 tasks in the list.\n", ui.addTask(toDo, 4));
    }

    /**
     * Tests emptyTaskList method.
     */
    @Test
    public void testEmptyTaskList() {
        assertEquals("You currently have no tasks in the list.\n", ui.emptyTaskList());
    }

    /**
     * Tests showTaskList method.
     */
    @Test
    public void testShowTaskList() {
        ToDo toDo1 = new ToDo("todo1");
        ToDo toDo2 = new ToDo("todo2");
        ToDo toDo3 = new ToDo("todo3");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(toDo1);
        tasks.add(toDo2);
        tasks.add(toDo3);
        TaskList taskList = new TaskList(tasks);
        // Test 1
        String expectedString = "Here are the matching tasks in your list:\n"
            + "1. [T][\u2718] todo1\n"
            + "2. [T][\u2718] todo2\n"
            + "3. [T][\u2718] todo3\n";
        assertEquals(expectedString, ui.showTaskList(taskList, "matching "));
        // Test 2
        expectedString = "Here are the tasks in your list:\n"
            + "1. [T][\u2718] todo1\n"
            + "2. [T][\u2718] todo2\n"
            + "3. [T][\u2718] todo3\n";
        assertEquals(expectedString, ui.showTaskList(taskList, ""));
    }

    /**
     * Tests emptyFind method.
     */
    @Test
    public void testEmptyFind() {
        assertEquals("There are no matching tasks with the keyword size.\n", ui.emptyFind("size"));
        assertEquals("There are no matching tasks with the keyword .\n", ui.emptyFind(""));
    }
}
