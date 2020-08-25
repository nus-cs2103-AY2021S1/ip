package tickbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTests {
    @Test
    public void testSpace() {
        LocalDate date = LocalDate.parse("2020-01-01");
        Deadline deadline = new Deadline(false, "with space", date);
        assertEquals(deadline.toString(), "[D][✘] with space (by: 2020-01-01)");
    }

    @Test
    public void testComma() {
        LocalDate date = LocalDate.parse("2020-01-01");
        Event event = new Event(false, "with, comma", date);
        assertEquals(event.toString(), "[E][✘] with, comma (at: 2020-01-01)");
    }

    @Test
    public void testBackSlash() {
        Todo todo = new Todo(true, "with \\ backslash");
        assertEquals(todo.toString(), "[T][✔︎] with \\ backslash");
    }
}