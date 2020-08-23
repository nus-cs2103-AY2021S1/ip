package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testSaveText() {
        assertEquals("T | 0 | test1", new Todo("test1").saveText(0));
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] test1", new Todo("test1").toString());
    }
}
