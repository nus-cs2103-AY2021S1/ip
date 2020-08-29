import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {

    @Test
    public void todo_normalInput_success() {
        ToDo todo = new ToDo("read book");
        assertEquals(todo.toString(), "[T][X] read book");
    }

    @Test
    public void deadline_normalInput_success() {
        Deadline deadline = new Deadline("read book", "2/12/2019 2300");
        assertEquals(deadline.toString(), "[D][X] read book (by: Dec 2 2019 11:00 PM)");
    }

    @Test
    public void deadline_differentDateFormat_success() {
        Deadline deadline = new Deadline("read book", "2019/12/2 2300");
        assertEquals(deadline.toString(), "[D][X] read book (by: Dec 2 2019 11:00 PM)");
    }

    @Test
    public void event_normalInput_success() {
        Event event = new Event ("read book", "2/12/2019 4-6pm");
        assertEquals(event.toString(), "[E][X] read book (at: 2/12/2019 4-6pm)");
    }
}
