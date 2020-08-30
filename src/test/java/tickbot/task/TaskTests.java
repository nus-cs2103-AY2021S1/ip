package tickbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTests {
    /**
     * Test if task can process content with spaces.
     */
    @Test
    public void testSpace() {
        LocalDate date = LocalDate.parse("2020-01-01");
        Deadline deadline = new Deadline(false, "with space", date);
        assertEquals("[D][✘] with space (by: 2020-01-01)", deadline.toString());
    }

    /**
     * Test if task can process content with commas.
     */
    @Test
    public void testComma() {
        LocalDate date = LocalDate.parse("2020-01-01");
        Event event = new Event(false, "with, comma", date);
        assertEquals("[E][✘] with, comma (at: 2020-01-01)", event.toString());
    }

    /**
     * Test if task can process content with back slashes.
     */
    @Test
    public void testBackSlash() {
        Todo todo = new Todo(true, "with \\ backslash");
        assertEquals("[T][✔︎] with \\ backslash", todo.toString());
    }
}
