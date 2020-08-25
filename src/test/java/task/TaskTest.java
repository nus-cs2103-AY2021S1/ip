package task;

import duke.Parser;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    Event event = new Event("e", "time");
    Deadline ddl = new Deadline("d", "time");
    Todo todo = new Todo("t");

    @Test
    public void toString_undoneEvent_success() {
        assertEquals("[E][✗] e (at: time)", event.toString());
        assertEquals("[D][✗] d (by: time)", ddl.toString());
        assertEquals("[T][✗] t", todo.toString());
    }

    @Test
    public void toString_doneEvent_success() {
        event.setDone();
        ddl.setDone();
        todo.setDone();
        assertEquals("[E][✓] e (at: time)", event.toString());
        assertEquals("[D][✓] d (by: time)", ddl.toString());
        assertEquals("[T][✓] t", todo.toString());
    }

}
