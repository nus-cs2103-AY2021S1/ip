import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskTest {

    @Test
    public void createTodo_validInput_success() {
        Todo todo = new Todo("create tests");
        String expected = "[T][✗] create tests";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void createDeadline_validInput_success() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        LocalDate date = LocalDate.parse("4/5/2020", dateFormatter);
        LocalTime time = LocalTime.parse("14:00", timeFormatter);
        Deadline deadline = new Deadline("create deadline", date, time);
        String expected = "[D][✗] create deadline (by: Mon, May 04 2020, 02:00 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void taskToFileString_validInput_success() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        LocalDate date = LocalDate.parse("10/10/2020", dateFormatter);
        LocalTime time = LocalTime.parse("10:00", timeFormatter);
        Event event = new Event("create event", date, time);
        String expected = "[E][✗] create event (at: Sat, Oct 10 2020, 10:00 AM)";
        assertEquals(expected, event.toString());
    }
}
