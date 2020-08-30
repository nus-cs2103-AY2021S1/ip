package botbot.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TodoTest {
    @Test
    public void getAt() {
        assertNull(new Todo("todo test").getAt());
        assertNull(new Todo("test", true).getAt());
        assertNull(new Todo("test", false).getAt());
    }

    @Test
    public void getBy() {
        assertNull(new Todo("todo test").getBy());
        assertNull(new Todo("test", true).getBy());
        assertNull(new Todo("test", false).getBy());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T] [\u2718] test", new Todo("todo test").toString());
        assertEquals("[T] [\u2713] sample", new Todo("sample", true).toString());
        assertEquals("[T] [\u2718] test", new Todo("test", false).toString());
    }
}
