package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toString_normal_formattedString() {
        Task t = new Task("test");
        assertEquals("[✘] test", t.toString());
    }
}
