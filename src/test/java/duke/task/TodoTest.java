package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    ToDo td = new ToDo("test");
    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] test", td.toString());
    }

}
