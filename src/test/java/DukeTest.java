import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    @DisplayName("Task class: Test for new todo created")
    public void todoTest() {
        Task todo = new ToDo("snooze");
        assertEquals("[T][✗] snooze", todo.toString());
    }

    @Test
    @DisplayName("Task class: Test for checking off events as done")
    public void doneTest() {
        Task todo = new ToDo("sleep");
        todo.markAsCompleted();
        assertEquals("[T][✓] sleep", todo.toString());
    }

    @Test
    @DisplayName("DukeInterpreter class: Test for encoding deadlines/events")
    public void encodeTest() throws DukeException {
        Task task = new Deadline("meeting", "2011-10-10 12:30");
        String encodedTask = DukeInterpreter.encode(task);
        assertEquals("D | ✗ | meeting | 2011-10-10T12:30",encodedTask);
    }

    @Test
    @DisplayName("DukeInterpreter class: Test for decoding deadlines/events")
    public void decodeTest() throws DukeException {
        String storedEvent = "E | ✓ | project meeting | 2011-12-12T08:55";
        Task event = DukeInterpreter.decode(storedEvent);
        assertEquals("[E][✓] project meeting (at: Dec 12 2011 08:55)", event.toString());
    }

    @Test
    @DisplayName("ByeCommand Class: Test exit command")
    public void exitTest() throws DukeException {
        boolean isCompleted = Parser.parse("bye").isCompleted();
        assertEquals(true, isCompleted);
    }
}
