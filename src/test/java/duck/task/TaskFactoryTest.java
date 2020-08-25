package duck.task;

import duck.exception.DuckException;
import duck.ui.Colour;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskFactoryTest {
    @Test
    public void createTodo() {
        String input = "todo read book";
        try {
            Task task = TaskFactory.createTaskFromInput(input);
            assertEquals(true, task instanceof Todo);
            assertEquals(false, task.getDone());
            assertEquals("read book", task.getDescription());
            assertEquals(Colour.Yellow("[T]") + Colour.Red("[\u2718] read book"), task.getStatus());
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
            assertEquals(Colour.Magenta("[D]")
                    + Colour.Red("[\u2718] read book")
                    + " (by: " + expectedFormattedDate + ")", task.getStatus());
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
            assertEquals(Colour.Cyan("[E]")
                    + Colour.Red("[\u2718] read book")
                    + " (at: " + expectedFormattedDate + ")", task.getStatus());
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
