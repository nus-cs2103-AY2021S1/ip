import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    @Test
    public void testTodo() {
        ToDo todo = new ToDo("read book");
        assertEquals(todo.toString(), "[T][✘] read book");
    }

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("read book", "2/12/2019 2300");
        assertEquals(deadline.toString(), "[D][✘] read book (by: Dec 2 2019 11:00 PM)");
    }

    @Test
    public void testMoreDeadline() {
        Deadline deadline = new Deadline("read book", "2019/12/2 2300");
        assertEquals(deadline.toString(), "[D][✘] read book (by: Dec 2 2019 11:00 PM)");
    }

    @Test
    public void testEvent() {
        Event event = new Event ("read book", "2/12/2019 4-6pm");
        assertEquals(event.toString(), "[E][✘] read book (at: 2/12/2019 4-6pm)");
    }
}
