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
        TaskList taskList = new TaskList();
        Todo firstTodo = new Todo("task");
        Todo secondTodo = new Todo("todo");
        Todo thirdTodo = new Todo("random");
        taskList.addTask(firstTodo);
        taskList.addTask(secondTodo);
        taskList.addTask(thirdTodo);

        Throwable exception = assertThrows(DukeException.class, () -> taskList.deleteTask(5));
        assertEquals("Oh no! Task number does not exist in task list.",
                exception.getMessage());
        assertEquals(firstTodo, taskList.deleteTask(1));
        assertEquals(thirdTodo, taskList.deleteTask(2));
        assertEquals(1, taskList.size());
    }

    @Test
    public void testFormatTaskForDatabase() throws DukeException {
        Todo todo = new Todo("Task");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("04-21-2020 23:45", formatter);
        Deadline deadline = new Deadline("read book", dateTime);

        assertEquals("T|0|Task", todo.formatTaskForDatabase());
        assertEquals("D|0|read book|2020-04-21T23:45", deadline.formatTaskForDatabase());
    }

    @Test
    public void testToStringForTimedTasks() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("04-21-2020 23:45", formatter);
        Deadline deadline = new Deadline("read book", dateTime);

        Event event = new Event("book read", dateTime);

        assertEquals("[D][\u2718] read book (by:Apr 21 2020 23:45)", deadline.toString());
        assertEquals("[E][\u2718] book read (at:Apr 21 2020 23:45)", event.toString());
    }
}
