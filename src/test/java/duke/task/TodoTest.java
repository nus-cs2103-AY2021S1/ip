package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        String expected = "[T][✘] eat";
        assertEquals(expected, new Todo("eat").toString());
    }

    @Test
    public void testToFileString() {
        String expected = "T | 0 | eat";
        assertEquals(expected, new Todo("eat").toFileString());
    }
}
