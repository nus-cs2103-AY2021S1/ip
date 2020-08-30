package duke;

import org.junit.jupiter.api.Test;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTodoString() {
        assertEquals("[T][✘] borrow book", new ToDo("borrow book", false).toString());
    }

    @Test
    public void testDeadlineString() {
        assertEquals("[D][✘] return book (by: Aug 25 2020)",
                new Deadline("return book", LocalDate.parse("2020-08-25"), false).toString());
    }

    @Test
    public void testEventString() {
        assertEquals("[E][✘] project meeting (at: Aug 26 2020)",
                new Event("project meeting", LocalDate.parse("2020-08-26"), false).toString());
    }
}
