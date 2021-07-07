import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;

public class TaskTest {
    @Test
    public void validToDoCreated() {
        ToDo todo = new ToDo("junk");
        assertEquals(todo.printTask(), "[T][" + "\u2718" + "] junk");
    }

    @Test
    public void validDeadlineCreated() {
        Deadline deadline = new Deadline("junk", LocalDateTime.now());
        assertEquals(deadline.printTask(), "[D][" + "\u2718" + "] junk (by: "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }

    @Test
    public void validEventCreated() {
        Event event = new Event("junk", "monday");
        assertEquals(event.printTask(), "[E][" + "\u2718" + "] junk (at: monday)");
    }
}
