import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void constructTodo_normalInput_success(){
        Todo todo = new Todo("borrow book");
        assertEquals(todo.toString(),
                "[T][✗] borrow book");
    }

    @Test
    void constructEvent_normalInput_success(){
        Event event = new Event("project meeting", "06-06-2020 11:00");
        assertEquals(event.toString(),
                "[E][✗] project meeting (at: 6 Jun 2020, 11:00 am)");
    }

    @Test
    void constructDeadline_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        assertEquals(deadline.toString(),
                "[D][✗] return book (by: 6 Aug 2020, 12:00 pm)");
    }

    @Test
    void completeTask_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        deadline.done();
        assertEquals(deadline.toString(),
                "[D][✓] return book (by: 6 Aug 2020, 12:00 pm)");
    }
}
