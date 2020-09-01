import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Todo;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][not done] read book", new Todo("read book", false).toString());
    }
}
