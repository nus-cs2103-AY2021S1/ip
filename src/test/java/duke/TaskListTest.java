package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import exception.DukeException;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testSize() {
        try {
            TaskList forTest = new TaskList(new LinkedList<>());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse("29/08/2020 1800", dateFormatter);
            forTest.addTask(new Task("Test Junit"));
            forTest.addTask(new Deadline("return book", date));
            forTest.addTask(new Event("Study", date));
            assertEquals(3, forTest.size());
        } catch (DukeException e) {
            assertEquals("OOPS!!! The task already exists in the list.\n",e.toString());
        }
    }


    @Test
    public void testAdd() {
        try {
            TaskList forTest = new TaskList(new LinkedList<>());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse("29/08/2020 1800", dateFormatter);
            forTest.addTask(new Task("Test Junit"));
            forTest.addTask(new Deadline("return book", date));
            forTest.addTask(new Event("Study", date));
            forTest.addTask(new Task("Random task"));
            assertEquals(4, forTest.size());
        } catch (DukeException e) {
            assertEquals("OOPS!!! The task already exists in the list.\n",e.toString());
        }
    }

    @Test
    public void testRemove() {
        TaskList forTest = new TaskList(new LinkedList<>());
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse("29/08/2020 1800", dateFormatter);
            forTest.addTask(new Task("Test Junit"));
            forTest.addTask(new Deadline("return book", date));
            forTest.addTask(new Event("Study", date));
        } catch (DukeException e) {
            assertEquals("OOPS!!! The task already exists in the list.\n",e.toString());
        }
        try {
            forTest.delete(1);
            assertEquals(2, forTest.size());
        } catch (Exception e) {
            assertEquals("OOPS!!! There are no such task.\n", e.toString());
        }
    }

    @Test
    public void testRemoveOutOfBound() {
        TaskList forTest = new TaskList(new LinkedList<>());
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse("29/08/2020 1800", dateFormatter);
            forTest.addTask(new Task("Test Junit"));
            forTest.addTask(new Deadline("return book", date));
            forTest.addTask(new Event("Study", date));
        } catch (DukeException e) {
            assertEquals("OOPS!!! The task already exists in the list.\n",e.toString());
        }
        try {
            forTest.delete(100);
            assertEquals(2, forTest.size());
        } catch (Exception e) {
            assertEquals("OOPS!!! There are no such task.\n", e.toString());
        }
    }

}
