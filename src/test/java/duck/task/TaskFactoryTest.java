package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duck.exception.DuckException;

public class TaskFactoryTest {
    @Test
    public void createTodo() {
        String input = "todo read book";
        try {
            Task task = TaskFactory.createTaskFromInput(input);
            assertEquals(true, task instanceof Todo);
            assertEquals(false, task.getDone());
            assertEquals("read book", task.getDescription());
            assertEquals("[T][\u2718] read book", task.getStatus());
        } catch (DuckException e) {
            fail();
        }
    }

    @Test
    public void createDeadline() {
        String input = "deadline read book /by 2020-12-12";
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        try {
            Task task = TaskFactory.createTaskFromInput(input);
            String expectedFormattedDate = fixedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals(true, task instanceof Deadline);
            assertEquals("[D][\u2718] read book (by: " + expectedFormattedDate + ")", task.getStatus());
            assertEquals(false, task.getDone());
            assertEquals("read book", task.getDescription());

            Deadline deadline = (Deadline) task;
            assertEquals(fixedDate, deadline.getDate());
            assertEquals(expectedFormattedDate, deadline.getDateAsString());

        } catch (DuckException e) {
            fail();
        }
    }

    @Test
    public void createEvent() {
        String input = "event read book /at 2020-12-12";
        LocalDate fixedDate = LocalDate.parse("2020-12-12");
        try {
            Task task = TaskFactory.createTaskFromInput(input);
            String expectedFormattedDate = fixedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            assertEquals(true, task instanceof Event);
            assertEquals("[E][\u2718] read book (at: " + expectedFormattedDate + ")", task.getStatus());
            assertEquals(false, task.getDone());
            assertEquals("read book", task.getDescription());

            Event event = (Event) task;
            assertEquals(fixedDate, event.getDate());
            assertEquals(expectedFormattedDate, event.getDateAsString());

        } catch (DuckException e) {
            fail();
        }
    }
}
