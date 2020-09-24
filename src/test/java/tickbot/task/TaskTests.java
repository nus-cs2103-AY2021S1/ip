package tickbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import tickbot.util.DateTimeFormatterFactory;

public class TaskTests {
    /**
     * Tests if task can process content with spaces.
     */
    @Test
    public void testSpace() {
        DateTimeFormatter formatter = DateTimeFormatterFactory.getInputFormatter();
        LocalDateTime date = LocalDateTime.parse("2020-01-01 08:00", formatter);
        Deadline deadline = new Deadline(false, "with space", date);
        assertEquals("[D][✘] with space (by: 2020-01-01 08:00:00)", deadline.toString());
    }

    /**
     * Tests if task can process content with commas.
     */
    @Test
    public void testComma() {
        DateTimeFormatter formatter = DateTimeFormatterFactory.getInputFormatter();
        LocalDateTime date = LocalDateTime.parse("2020-01-01", formatter);
        Event event = new Event(false, "with, comma", date);
        assertEquals("[E][✘] with, comma (at: 2020-01-01 00:00:00)", event.toString());
    }

    /**
     * Tests if task can process content with back slashes.
     */
    @Test
    public void testBackSlash() {
        Todo todo = new Todo(true, "with \\ backslash");
        assertEquals("[T][✔︎] with \\ backslash", todo.toString());
    }
}
