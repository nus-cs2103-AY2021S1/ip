package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private ToDo td = new ToDo("test");
    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] test", td.toString());
    }

}
