package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void getTasks() {
        TaskList list = new TaskList();
        list.addTask("test content", "todo");
        assertEquals(list.getTasks().get(0).toString(), "[T][✗] test content");
    }

    @Test
    void printList() {
        TaskList list = new TaskList();
        list.addTask("test content1", "todo");
        list.addTask("test content2", "deadline", "2020-08-23");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        list.printList();
        String expectedOut =
                "Here are the tasks in your list:\n" +
                "1.[T][✗] test content1\n" +
                "2.[D][✗] test content2 (by: Aug 23 2020)\n";
        assertEquals(expectedOut, outContent.toString());
    }

    @Test
    void markTaskAsDone() {
        TaskList list = new TaskList();
        list.addTask("test content1", "todo");
        list.markTaskAsDone(1);
        assertTrue(list.getTasks().get(0).isCompleted());
    }

    @Test
    void addTask() {
        TaskList list = new TaskList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        list.addTask("test content1", "todo");
        list.addTask("test content2", "deadline", "2020-08-23");
        String expectedOut = "Got it. I've added this task:\n" +
                "    [T][✗] test content1\n" +
                "Now you have 1 task in the list.\n" +
                "Got it. I've added this task:\n" +
                "    [D][✗] test content2 (by: Aug 23 2020)\n" +
                "Now you have 2 tasks in the list.\n";
        assertEquals(expectedOut, outContent.toString());
        assertEquals(list.getTasks().size(), 2);
    }

    @Test
    void deleteTask() {
        TaskList list = new TaskList();
        list.addTask("test content1", "todo");
        list.addTask("test content2", "deadline", "2020-08-23");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            list.deleteTask(1);
            String expected = "Noted. I've removed this task:\n" +
                    "[T][✗] test content1\n" +
                    "Now you have 1 task in the list.\n";
            assertEquals(expected, outContent.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void deleteTask_invalidIndex_exceptionThrown() {
        TaskList list = new TaskList();
        try {
            list.deleteTask(1);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! Seems the index you provided is not in the list.");
        }
    }

    @Test
    void findTask() {
        TaskList list = new TaskList();
        list.addTask("test content1", "todo");
        list.addTask("test content2", "deadline", "2020-08-23");
        list.addTask("test content2", "deadline", "2020-08-25");
        list.addTask("test content3", "deadline", "2020-08-28");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.findTask("content2");

        String expected = "Here are the matching tasks in your list:\n" +
                "1.[D][✗] test content2 (by: Aug 23 2020)\n" +
                "2.[D][✗] test content2 (by: Aug 25 2020)\n";
        assertEquals(expected, outContent.toString());
    }
}