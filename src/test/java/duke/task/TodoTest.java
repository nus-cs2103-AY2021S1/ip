package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void toStringTest() {
        String expected = String.format("[T] [\u2718] Test");
        assertEquals(expected, new Todo("Test", false).toString());
    }
}
