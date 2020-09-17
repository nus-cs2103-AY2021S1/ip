package botbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T] [\u2718] test", new Todo("test").toString());
        assertEquals("[T] [\u2713] sample", new Todo("sample", TaskStatus.DONE).toString());
        assertEquals("[T] [\u2718] test", new Todo("test", TaskStatus.NOT_DONE).toString());
    }
}
