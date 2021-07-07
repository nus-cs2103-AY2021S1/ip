package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class TaskTest {
    @Test
    public void testSetDone() throws DukeException {
        Todo todo = new Todo("task");
        todo.setDone();
        assertEquals(true, todo.getDoneStatus());

    }

    @Test
    public void testDeleteTask() throws DukeException {
        TaskList tasks = new TaskList();
        Todo firstTodo = new Todo("task");
        Todo secondTodo = new Todo("todo");
        Todo thirdTodo = new Todo("random");
        tasks.addTask(firstTodo);
        tasks.addTask(secondTodo);
        tasks.addTask(thirdTodo);

        Throwable exception = assertThrows(DukeException.class, () -> tasks.deleteTask(5));
        assertEquals("Oh no! Task number does not exist in task list.",
                exception.getMessage());
        assertEquals(firstTodo, tasks.deleteTask(1));
        assertEquals(thirdTodo, tasks.deleteTask(2));
        assertEquals(1, tasks.size());
    }

    @Test
    public void testFormatTaskForDatabase() throws DukeException {
        Todo todo = new Todo("Task");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("04-21-2020 23:45", formatter);
        Deadline deadline = new Deadline("read book", dateTime);

        assertEquals("MID|T|0|Task", todo.formatTaskForDatabase());
        assertEquals("MID|D|0|read book|2020-04-21T23:45", deadline.formatTaskForDatabase());
    }

    @Test
    public void testToStringForTimedTasks() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("04-21-2020 23:45", formatter);
        Deadline deadline = new Deadline("read book", dateTime);

        Event event = new Event("book read", dateTime);

        assertEquals("[MID][D][\u2718] read book (By: Apr 21 2020 23:45)", deadline.toString());
        assertEquals("[MID][E][\u2718] book read (At: Apr 21 2020 23:45)", event.toString());
    }
}
